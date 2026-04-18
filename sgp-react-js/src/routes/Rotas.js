import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";

import Atividades from "../pages/Atividades/Atividades";
import Dashboard from "../pages/Dashboard/Dashboard";
import Login from "../pages/Login/Login";
import Page404 from "../pages/Page404/Page404";

class Rotas extends React.Component {
    render() {
        return (
            <>
                <BrowserRouter>
                    <Routes>
                        <Route index path="/" element={<Login />} />
                        <Route path="/login" element={<Login />} />
                        <Route path="/logout" element={<Login action="logout" />} />
                        <Route path="/dashboard" element={<Dashboard />} />
                        <Route path="/atividades" element={<Atividades />} />
                        {/* <Route path="/usuarios" element={<Usuarios />} />
                        <Route path="/novo-usuario" element={<UsuarioForm />} />
                        <Route path="/usuario/:id" element={<UsuarioForm />} />
                        <Route path="/projetos" element={<Projetos />} />
                        <Route path="/novo-projeto" element={<ProjetoForm />} />
                        <Route path="/projeto/:id" element={<ProjetoForm />} /> */}
                        <Route path="*" element={<Page404 />} />
                    </Routes>
                </BrowserRouter>
            </>
        );
    }
}

export default Rotas;
