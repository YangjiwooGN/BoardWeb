package com.hyundai.cloud.service;

import com.hyundai.cloud.document.UserDocument;
import com.hyundai.cloud.dto.ResponseDto;
import com.hyundai.cloud.dto.SignInDto;
import com.hyundai.cloud.dto.SignInResponseDto;
import com.hyundai.cloud.dto.SignUpDto;
import com.hyundai.cloud.entity.UserEntity;
import com.hyundai.cloud.repository.UserRepository;
import com.hyundai.cloud.repository.UserMongoRepository;
import com.hyundai.cloud.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserMongoRepository userMongoRepository;
    @Autowired
    private UserKafkaProducerService userKafkaProducer;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenProvider tokenProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public ResponseDto<?> signUp(SignUpDto dto){
        String userEmail = dto.getUserEmail();
        String userPassword = dto.getUserPassword();
        String userPasswordCheck = dto.getUserPasswordCheck();

        try{
            // email 중복 확인
            if(userMongoRepository.existsById(userEmail)){
                return ResponseDto.setFailed("Existed Email");
            }
        }catch (Exception e){
            return ResponseDto.setFailed("Database Error");
        }

        if(!userPassword.equals(userPasswordCheck)){
            return ResponseDto.setFailed("Password does not matched");
        }

        // UserEntity 생성
        UserEntity userEntity = new UserEntity(dto);
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(userPassword);
        userEntity.setUserPassword(encodedPassword);

        try{
            // UserRepository를 이용해서 데이터베이스에 Entity 저장
            userRepository.save(userEntity);
            // Kafka에 발행
            userKafkaProducer.sendUser(userEntity);
        }catch (Exception e){
            return ResponseDto.setFailed("Database Error");
        }

        // 성공시 success response 반환
        return ResponseDto.setSuccess("Sign Up Success", null);
    }

    public ResponseDto<SignInResponseDto> signIn(SignInDto dto){
        String userEmail = dto.getUserEmail();
        String userPassword = dto.getUserPassword();

        UserEntity userEntity = null;
        try {
            userEntity = userRepository.findByUserEmail(userEmail);
            // 잘못된 이메일
            if(userEntity == null){
                return ResponseDto.setFailed("Sign In Failed");
            }
            // 잘못된 비밀번호
            if(!passwordEncoder.matches(userPassword, userEntity.getUserPassword())){
                return ResponseDto.setFailed("Sign In Failed");
            }
        }catch (Exception e){
            return ResponseDto.setFailed("Database Error");
        }
        userEntity.setUserPassword("");

        String token = tokenProvider.create(userEmail);
        int exprTime = 3600000;

        SignInResponseDto signInResponseDto = new SignInResponseDto(token, exprTime, userEntity);
        return ResponseDto.setSuccess("Sign In Success", signInResponseDto);
    }

    public boolean isNicknameDuplicate(String userNickname) {
        return userMongoRepository.existsByUserNickname(userNickname);
    }

    public boolean verifyUserPassword(String userEmail, String userPassword) {
        UserDocument user = userMongoRepository.findByUserEmail(userEmail);
        if (user != null) {
            return passwordEncoder.matches(userPassword, user.getUserPassword());
        }
        return false;
    }


    public UserDocument getUserByEmail(String userEmail) {
        return userMongoRepository.findByUserEmail(userEmail);
    }

    public ResponseDto<?> updateUserInfo(String userEmail, UserEntity updatedInfo) {
        try {
            // 기존 사용자 정보 불러오기
            UserEntity currentUser = userRepository.findByUserEmail(userEmail);

            // 사용자 정보가 없으면 오류 반환
            if (currentUser == null) {
                return ResponseDto.setFailed("User not found");
            }

            // 수정된 정보 반영
            currentUser.setUserNickname(updatedInfo.getUserNickname());
            currentUser.setUserName(updatedInfo.getUserName());
            currentUser.setUserAddress(updatedInfo.getUserAddress());
            currentUser.setUserPhoneNumber(updatedInfo.getUserPhoneNumber());

            // 데이터베이스에 저장
            userRepository.save(currentUser);

            return ResponseDto.setSuccess("User info updated successfully", null);

        } catch (Exception e) {
            return ResponseDto.setFailed("Database Error");
        }
    }

}

