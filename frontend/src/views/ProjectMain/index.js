import React from "react";
import {
    Box,
    Container,
    Typography,
    Paper,
    Grid,
    Tabs,
    Tab,
    Divider
} from "@mui/material";

export default function ProjectMain() {
    return (
        <Box sx={{ backgroundColor: '#121212', color: 'white', minHeight: '100vh', py: 6 }}>
            <Container>
                {/* 제목 */}
                <Box textAlign="center" mb={8}>
                    <Typography variant="h2" fontWeight={900} gutterBottom>
                        현대오토에버 모빌리티 SW 스쿨 2기
                    </Typography>
                    <Typography variant="h6" color="gray">
                        HYUNDAI AUTOEVER Mobility SW School
                    </Typography>
                </Box>

                {/* 교육일정 & 신청기간 */}
                <Paper elevation={5} sx={{ p: 5, mb: 8, borderRadius: 4 }}>
                    <Grid container spacing={4} justifyContent="center" textAlign="center">
                        <Grid item xs={12} md={6}>
                            <Typography variant="h6" color="secondary" gutterBottom>
                                🕒 교육일정
                            </Typography>
                            <Typography variant="h5" fontWeight={700}>
                                2025.04.28.(월) ~ 2025.11.12.(수)
                            </Typography>
                            <Typography variant="body2" mt={1}>
                                (공휴일 및 일부 주말 제외, 1일 8시간, 총 6개월 / 1,000시간)
                            </Typography>
                        </Grid>
                        <Grid item xs={12} md={6}>
                            <Typography variant="h6" color="secondary" gutterBottom>
                                📥 신청기간
                            </Typography>
                            <Typography variant="h5" fontWeight={700}>
                                2025.03.04.(화) 9시 ~ 2025.04.07.(월) 13시
                            </Typography>
                            <Typography variant="body2" mt={1}>
                                - 코딩테스트 : 4월 중순<br />
                                - 면접전형 : 4월 중순<br />
                                - 입과식 : 4월 25일(금)
                            </Typography>
                            <Typography variant="caption" color="error">
                                * 일정은 변경될 수 있으며, 각 전형별 세부 일정은 합격자 대상 안내 예정
                            </Typography>
                        </Grid>
                    </Grid>
                </Paper>

                {/* 혜택 */}
                <Box mb={8}>
                    <Typography variant="h4" textAlign="center" fontWeight={800} mb={5}>
                        현대오토에버 모빌리티 SW 스쿨 클래스메이트 혜택
                    </Typography>
                    <Grid container spacing={3} justifyContent="center">
                        {[
                            "고용노동부 \n내일배움카드 발급 \n대상 무료교육",
                            "수료생 대상 \n별도 채용전형 진행",
                            "프로젝트 우수자 \n현대오토에버 \n대표이사 명의 상장 수여",
                            "현대오토에버 사업장 등 \n방문 견학 예정",
                            "자동차 개발 환경과 \n유사한 임베디드 실습 및 \n프로젝트 환경 제공",
                            "현직자 직무 \nTech Talk 및 \n인사담당자 설명회"
                        ].map((text, idx) => (
                            <Grid item xs={12} sm={6} md={4} key={idx}>
                                <Paper
                                    elevation={3}
                                    sx={{
                                        p: 1,
                                        borderRadius: 4,
                                        height: '100%',
                                        minHeight: 180,
                                        textAlign: 'center',
                                        display: 'flex',
                                        flexDirection: 'column',
                                        justifyContent: 'center',
                                        alignItems: 'center',
                                        backgroundColor: '#f0f0f0',
                                        color: '#333'
                                    }}
                                >
                                    <Box
                                        sx={{
                                            width: 48,
                                            height: 48,
                                            borderRadius: '50%',
                                            backgroundColor: '#4fc3f7',
                                            display: 'flex',
                                            justifyContent: 'center',
                                            alignItems: 'center',
                                            mb: 1
                                        }}
                                    >
                                        <Typography fontWeight={700}>0{idx + 1}</Typography>
                                    </Box>
                                    <Typography variant="body1" sx={{ whiteSpace: 'pre-line', fontWeight: 500 }}>
                                        {text}
                                    </Typography>
                                </Paper>
                            </Grid>
                        ))}
                    </Grid>
                </Box>

                {/* 교육계획 */}
                <Box>
                    <Typography variant="h4" textAlign="center" fontWeight={800} mb={5}>
                        교육계획 - 클라우드 과정
                    </Typography>
                    <Paper elevation={5} sx={{ p: 4, borderRadius: 4, backgroundColor: '#f8f8f8', color: '#222' }}>
                        <Grid container spacing={2} justifyContent="center">
                            {[
                                {
                                    title: "1개월차 (160H)",
                                    contents: [
                                        "파이썬 프로그래밍 (40H)",
                                        "MariaDB 서버 구축 및 관리 (40H)",
                                        "리눅스 기본/시스템 관리 (48H)",
                                        "bash 쉘 프로그래밍 (16H)",
                                        "네트워크 개념 및 프로토콜 (16H)"
                                    ]
                                },
                                {
                                    title: "2개월차 (160H)",
                                    contents: [
                                        "네트워크 장비 (20H)",
                                        "방화벽 설정 (20H)",
                                        "클라우드 환경 이해 (16H)",
                                        "보안, 가상화 기술 (48H)",
                                        "Kubernetes (40H)"
                                    ]
                                },
                                {
                                    title: "3개월차 (160H)",
                                    contents: [
                                        "Kubernetes (32H)",
                                        "컨테이너 이미지 저장소 (16H)",
                                        "CI/CD 도구 (56H)",
                                        "프라이빗/퍼블릭 클라우드 (40H)"
                                    ]
                                },
                                {
                                    title: "4개월차 (160H)",
                                    contents: [
                                        "퍼블릭 클라우드 (20H)",
                                        "하이브리드 클라우드 (24H)",
                                        "워크로드 모니터링 및 운영 도구 (60H)",
                                        "테스트/운영 앱 실습 (16H)"
                                    ]
                                },
                                {
                                    title: "5개월차 (160H)",
                                    contents: ["워크로드 운영 도구 실습"]
                                },
                                {
                                    title: "6개월차 (200H)",
                                    contents: ["실제 환경 기반 프로젝트"]
                                }
                            ].map((item, idx) => (
                                <Grid item xs={12} sm={6} md={4} key={idx}>
                                    <Paper elevation={1} sx={{ p: 2, borderRadius: 3, textAlign: 'center', backgroundColor: '#fff' }}>
                                        <Typography variant="h6" fontWeight={600} gutterBottom>
                                            {item.title}
                                        </Typography>
                                        {item.contents.map((c, i) => (
                                            <Typography variant="body2" key={i}>{c}</Typography>
                                        ))}
                                    </Paper>
                                </Grid>
                            ))}
                        </Grid>
                        <Divider sx={{ mt: 4, mb: 1 }} />
                        <Typography variant="caption" color="gray" display="block" textAlign="center">
                            * 커리큘럼 및 프로젝트는 상황에 따라 일부 변경될 수 있습니다.
                        </Typography>
                    </Paper>
                </Box>
            </Container>
        </Box>
    );
}
