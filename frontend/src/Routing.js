import React from "react";
import {Route, Routes} from "react-router-dom";
import ProjectMain from "./views/ProjectMain";
import Board from "./views/Board";
import BoardAdd from "./components/board/BoardAdd";
import BoardDetail from "./components/board/BoardDetail";
import BoardEdit from "./components/board/BoardEdit";

export default function Routing(){
    return (
        <div>
            <div style={{ marginTop: '90px' }}>
                <Routes>
                    <Route path='/' element={<ProjectMain/>} />
                    <Route path='/board' element={<Board/>} />
                    <Route path='/boardAdd' element={<BoardAdd />} />
                    <Route path="/boardDetail/:boardId" element={<BoardDetail />} />
                    <Route path="/board/edit/:boardId" element={<BoardEdit/>}/>
                </Routes>
            </div>
        </div>
    )
}