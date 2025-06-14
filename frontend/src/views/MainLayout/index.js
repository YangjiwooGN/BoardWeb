import React, {useEffect, useState} from "react";
import Navigation from "../Navigation";
import Authentication from "../Authentication";
import {useUserStore} from "../../stores";
import {useCookies} from "react-cookie";
import axios from "axios";
import Routing from "../../Routing";
import { BrowserRouter } from "react-router-dom";

export default function MainLayout(){
    const [projectResponse, setProjectResponse] = useState('');
    const [cookies] = useCookies();
    const {user} = useUserStore();

    const getProject = async (token) => {
        const requestOption = {
            headers: {
                Authorization: `Bearer ${token}`
            }
        };
        await axios.get('http://localhost:8080/api/project/', requestOption).then((response) => {
            setProjectResponse(response.data);
        }).catch((error) => '');
    }

    useEffect(() => {
        const token = cookies.token;
        if(token){
            getProject(token);
        }else{
            setProjectResponse('');
        }
    }, [cookies.token, projectResponse]);

    return (
        <BrowserRouter>
            <>
                {projectResponse ? (<Navigation />) : ""}
                {projectResponse ? (<Routing />) : (<Authentication />)}
            </>
        </BrowserRouter>
    )
}