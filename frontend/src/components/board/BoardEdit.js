import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';
import {Button, TextField, Container, Typography, Box, Paper} from '@mui/material';
import community from "../../assets/communication_bg.png";
import {useCookies} from "react-cookie";

const BoardEdit = () => {
    const [board, setBoard] = useState({ boardTitle: '', boardContent: '' });
    const { boardId } = useParams();
    const navigate = useNavigate();
    const [cookies] = useCookies(['token']);

    useEffect(() => {
        const token = cookies.token;
        const fetchBoardDetail = async () => {
            try {
                const response = await axios.get(`/board/${boardId}`, {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });
                setBoard(response.data);
            } catch (error) {
                console.error("Error fetching board details:", error);
            }
        };

        fetchBoardDetail();
    }, [boardId]);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setBoard(prevState => ({ ...prevState, [name]: value }));
    };

    const handleUpdate = async () => {
        const token = cookies.token;
        try {
            const formData = new FormData();
            formData.append('boardTitle', board.boardTitle);
            formData.append('boardContent', board.boardContent);

            await axios.put(`/board/${boardId}`, formData, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            navigate(`/boardDetail/${boardId}`);
            alert("수정이 완료되었습니다. ")
        } catch (error) {
            console.error("Error updating board:", error);
        }
    };

    return (
        <div
            style={{backgroundImage: `url(${community})`,
                backgroundPosition: 'center',
                backgroundSize: 'cover',
                backgroundRepeat: 'no-repeat',
                width: '100vw',
                minHeight: 'calc(100vh + 50px)',
                marginTop:"-5%",
                paddingBottom: "3%"}}>
            <div style={{ paddingTop: '150px' }}>
                <Container maxWidth="md">
                    <Paper elevation={3} style={{ padding: '20px', borderRadius: '8px' }}>
                        <Typography variant="h4" gutterBottom align="center">
                            게시물 수정하기
                        </Typography>

                        <TextField
                            fullWidth
                            label="제목"
                            name="boardTitle"
                            variant="outlined"
                            value={board.boardTitle}
                            onChange={handleInputChange}
                            style={{ marginBottom: '20px' }}
                        />

                        <TextField
                            fullWidth
                            label="내용"
                            name="boardContent"
                            variant="outlined"
                            multiline
                            rows={4}
                            value={board.boardContent}
                            onChange={handleInputChange}
                        />

                        <Button variant="contained" color="primary" onClick={handleUpdate} style={{ marginTop: '20px' }}>
                            수정하기
                        </Button>
                    </Paper>
                </Container>
            </div>
        </div>
    );
}

export default BoardEdit;
