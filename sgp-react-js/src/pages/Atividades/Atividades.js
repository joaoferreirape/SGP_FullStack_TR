import React from "react";

import "./Atividades.css";

import MenuTopo from "../../components/MenuTopo";

class Atividades extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            list: [],
            carregando: true,
            erro: null,
        };
    }

    componentDidMount() {
        this.carregarAtividades();
    }

    carregarAtividades = async () => {
        try {
            const resposta = await fetch(
                "http://localhost:8080/api/atividades"
            );

            if (!resposta.ok) {
                throw new Error("Falha ao carregar atividades");
            }

            const dados = await resposta.json();

            this.setState({
                lista: dados,
                carregando: false,
            });
        } catch (erro) {
            this.setState({
                erro: erro.message,
                carregando: false,
            });
        }
    };

    render() {
        const { lista, carregando, erro } = this.state;
        return (
            <>
                <MenuTopo />

                <div className="container mt-4 card">
                    <h2>Lista de Atividades</h2>

                    {carregando && <div>Carregando...</div>}
                    {erro && <div className="alert alert-danger">{erro}</div>}

                    {!carregando && !erro && (
                        <table className="table table-bordered table-striped mt-3">
                            <thead className="table-light">
                                <tr>
                                    <th>ID</th>
                                    <th>Título</th>
                                    <th>Status</th>
                                    <th>Responsável</th>
                                    <th>Data Início</th>
                                    <th>Data Proposta</th>
                                    <th>Fim Encerramento</th>
                                </tr>
                            </thead>
                            <tbody>
                                {lista.map((item) => (
                                    <tr key={item.id}>
                                        <td>{item.id}</td>
                                        <td>{item.titulo}</td>
                                        <td>{item.status?.nome}</td>
                                        <td>{item.responsavel?.nome}</td>
                                        <td>
                                            {item.dataInicio?.substring(0, 10)}
                                        </td>
                                        <td>
                                            {item.dataFimProposta?.substring(
                                                0,
                                                10
                                            )}
                                        </td>
                                        <td>
                                            {item.dataFimEncerramento?.substring(
                                                0,
                                                10
                                            )}
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    )}
                </div>
            </>
        );
    }
}

export default Atividades;
