import React, {useState} from "react";
import SignUp from "../../components/login/SignUp";
import SignIn from "../../components/login/SignIn";
import {Box} from "@mui/material";

export default function Authentication(){
    // authView : true - signUp / false - signIn
    const [authView, setAuthView] = useState(false);
    return (
        <>
            <Box display='flex' height="100vh">
                <Box flex={1} display='flex' justifyContent='center' alignItems='center'>

                </Box>
                <Box flex={1} display='flex' justifyContent='center' alignItems='center'>
                    {authView ? (<SignUp setAuthView={setAuthView}/>) : (<SignIn setAuthView={setAuthView}/>)}
                </Box>
            </Box>
        </>
    )
}