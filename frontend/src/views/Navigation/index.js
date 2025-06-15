import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import {useUserStore} from "../../stores";
import {Link, useNavigate} from 'react-router-dom';
import {useCookies} from "react-cookie";
import {useEffect, useRef, useState} from "react";
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import axios from "axios";

export default function Navigation() {
    const [cookies, setCookies] = useCookies(['token']);
    const { user, setUser, removeUser } = useUserStore();
    const [userNickname, setUserNickname] = useState(user ? user.userNickname : "");
    const navigate = useNavigate();

    const [anchorEl, setAnchorEl] = useState(null);
    const profileBtnRef = useRef(null);

    const logOutHandler = () => {
        setCookies('token', '', {expires: new Date()});
        removeUser();
        navigate('/');
    }

    const handleProfileClick = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleMenuClose = () => {
        setAnchorEl(null);
    };

    useEffect(() => {
        if (cookies.token) {
            axios.get('/api/auth/currentUser', {
                headers: {
                    Authorization: `Bearer ${cookies.token}`
                }
            })
                .then(response => {
                    const fetchedUser = response.data;
                    // 사용자 정보를 상태에 저장
                    setUser(fetchedUser);
                    setUserNickname(fetchedUser.userNickname || "");
                })
                .catch(error => {
                    console.error("Error fetching user data:", error);
                });
        }
    }, []);

    useEffect(() => {
        if (user) {
            setUserNickname(user.userNickname || "");
        }
    }, [user]);

    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="fixed" sx={{ backgroundColor: '#ffffff', color: 'black' }}>
                <Toolbar>
                    <IconButton
                        size="large"
                        edge="start"
                        color="inherit"
                        aria-label="menu"
                        sx={{ mr: 29 }}
                    >
                        <Link to="/" style={{ textDecoration: 'none', color: 'inherit' }}>
                            <img alt="4thLogo" src="/assets/rapa.png" height='60' style={{margin: '0px 10px 0px 0px'}}/>
                        </Link>
                    </IconButton>
                    <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                        <Button variant="text" color="inherit" sx={{ marginRight: '20px' }}>
                            <Link to="/board" style={{ textDecoration: 'none', color: 'inherit' }}>
                                <Typography variant="h6" fontWeight="bold">
                                    커뮤니티
                                </Typography>
                            </Link>
                        </Button>
                    </Typography>
                    {user ? (
                        <>
                            <Typography variant="h6" sx={{ marginRight: '10px' }} onClick={handleProfileClick}>
                                {userNickname}
                            </Typography>
                            <Menu
                                anchorEl={anchorEl}
                                open={Boolean(anchorEl)}
                                onClose={handleMenuClose}
                            >
                                <MenuItem onClick={() => {
                                    logOutHandler();
                                    handleMenuClose();
                                }}>
                                    로그아웃
                                </MenuItem>
                            </Menu>
                        </>
                    ) : (
                        <Button color="inherit" onClick={() => logOutHandler()}>
                            <Typography variant="h6" fontWeight="bold">
                                로그인
                            </Typography>
                        </Button>
                    )}
                </Toolbar>
            </AppBar>
        </Box>
    );
}