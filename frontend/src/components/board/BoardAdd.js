import React, { useState, useEffect } from 'react';
import axios from 'axios';
import {Box, Button, TextField, Typography, Avatar, Container, Paper} from '@mui/material';
import { useCookies } from "react-cookie";
import { useNavigate } from 'react-router-dom';
import community from "../../assets/communication_bg.png";

const BoardAdd = () => {
    const [cookies] = useCookies(['token']);
    const [user, setUser] = useState({});
    const [postTitle, setPostTitle] = useState('');
    const [postContent, setPostContent] = useState('');
    const navigate = useNavigate();
    const [isLoading, setIsLoading] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');

    useEffect(() => {
        const fetchUserData = async () => {
            const token = cookies.token;

            if (token) {
                try {
                    const userDetails = await axios.get('/api/auth/currentUser', {
                        headers: {
                            Authorization: `Bearer ${token}`
                        }
                    });
                    setUser(userDetails.data);
                } catch (error) {
                    console.error("Error fetching user data:", error);
                }
            }
        };

        fetchUserData();
    }, [cookies.token]);

    const handleCreatePost = async () => {
        if (!postTitle || !postContent) {
            alert('제목과 내용을 모두 작성해주세요.');
            return;
        }

        setIsLoading(true);
        setErrorMessage('');

        const formData = new FormData();
        formData.append('boardTitle', postTitle);
        formData.append('boardWriterEmail', user.userEmail);
        formData.append('boardWriterNickname', user.userNickname);
        formData.append('boardContent', postContent);

        try {
            const response = await axios.post('/board/create', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                    'Authorization': `Bearer ${cookies.token}`
                }
            });

            if (response.data.success) {
                alert('게시글 작성이 완료되었습니다.');
                navigate('/board');
            } else {
                setErrorMessage('게시글 작성 중 오류가 발생했습니다.');
            }
        } catch (error) {
            setErrorMessage('게시글 작성 중 오류가 발생했습니다.');
        } finally {
            setIsLoading(false);
        }
    };

    return (
        <div align="center"
             style={{backgroundImage: `url(${community})`,
                 backgroundPosition: 'center',
                 backgroundSize: 'cover',
                 backgroundRepeat: 'no-repeat',
                 width: '100vw',
                 minHeight: 'calc(100vh + 50px)',
                 marginTop:"-5%",
                 paddingBottom: "3%"}}>
            <div style={{ paddingTop: '150px' }}>
                <Container style={{ maxWidth: '800px' }}>
                    <Paper elevation={3} style={{ padding: '20px', borderRadius: '8px' }}>
                        <Typography variant="h5" gutterBottom align="center">
                            글 작성
                        </Typography>

                        <Box display="flex" flexDirection="column" alignItems="center" marginBottom="20px">
                            <Typography variant="h6" gutterBottom>
                                {user.userNickname}
                            </Typography>
                        </Box>

                        <TextField
                            fullWidth
                            label="제목"
                            variant="outlined"
                            value={postTitle}
                            onChange={(e) => setPostTitle(e.target.value)}
                            style={{ marginBottom: '20px' }}
                        />

                        <TextField
                            fullWidth
                            label="내용"
                            variant="outlined"
                            multiline
                            rows={8}
                            value={postContent}
                            onChange={(e) => setPostContent(e.target.value)}
                            style={{ marginBottom: '10px' }}
                        />

                        {errorMessage && (
                            <Box mt={2} marginBottom="20px">
                                <Typography color="error">{errorMessage}</Typography>
                            </Box>
                        )}

                        <Box mt={2}>
                            <Button fullWidth variant="contained" color="primary" onClick={handleCreatePost} disabled={isLoading}>
                                {isLoading ? '게시 중...' : '게시'}
                            </Button>
                        </Box>
                    </Paper>
                </Container>
            </div>
        </div>
    );
}

export default BoardAdd;
