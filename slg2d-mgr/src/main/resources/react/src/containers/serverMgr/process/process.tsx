import * as React from 'react';
import { connect } from 'react-redux';

import { bindActionCreators, Dispatch } from 'redux';
import { Button, Col, Modal, Row, Table } from 'antd';
import { History } from 'history';

import { delProcess, IDelProcessData, saveProcessFilters } from '../../../sagas/serverMgr/processMgr';
import { ICombineState } from '../../../redux/reducer';
import { ADD_GAME_SERVER, CHG_GAME_SERVER } from '../../../const/funcType';
import { IProcess } from '../../../redux/modules/serverMgr/process';
import { IPlatform } from '../../../redux/modules/serverMgr/platform';
import { ICommCfg } from '../../../redux/modules/serverMgr/commCfg';
import { IReqAction } from '../../../sagas/ibaseaction';

const ButtonGroup = Button.Group;

const confirm = Modal.confirm;

export const WORLD = 1;
export const HOME = 2;
export const CHAT = 3;
export const PUBLIC = 4;
export const GATE = 5;
export const BATTLE_LOGIC = 6;

interface IStateProps {
    history: History;
    processes: IProcess[];
    platforms: IPlatform[];
    commCfgs: ICommCfg[];

    filter4Cluster: string[];
    filter4ProcessType: string[];
}

interface IDispatchProps {
    delProcess(ip: string, processType: number): IReqAction<IDelProcessData>;

    saveProcessFilters(filters: {}): {};
}

type IAppProps = IStateProps & IDispatchProps;

function mapStateToProps(state: ICombineState) {
    return {
        processes: state.serverMgr.process.servers,
        platforms: state.serverMgr.platform.platforms,
        commCfgs: state.serverMgr.commCfg.commCfgs,
        filter4Cluster: state.serverMgr.process.filter4Cluster,
        filter4ProcessType: state.serverMgr.process.filter4ProcessType,
    };
}

const mapDispatchToProps = (dispatch: Dispatch<{}>) => {
    return bindActionCreators({
        delProcess,
        saveProcessFilters,
    }, dispatch);
};

interface IState {

}

interface IFilter {
    text: string;
    value: string;
}

class ProcessMgr extends React.Component<IAppProps, IState> {

    constructor(props: IAppProps) {
        super(props);

        this.state = {};
    }

    delConfirm = (ip: string, processType: number) => {
        confirm({
            title: '删除设备配置',
            content: '你确定要删除这条记录？',
            onOk: () => {
                this.props.delProcess(ip, processType);
            },
        });
    };

    chgServer = (path: string) => {
        this.props.history.push(path);
    };

    switch2Add = () => {
        this.props.history.push(ADD_GAME_SERVER);
    };

    onTableChange = (pagination: {}, filters: {}) => {
        // 保存过滤选项
        this.props.saveProcessFilters(filters);
    };

    render() {
        const clusterFilterDatas: IFilter[] = [];
        const clusterMap = new Map<string, boolean>();
        const processes = this.props.processes;

        const commCfgNameMap = new Map<number, string>();
        const commCfgs = this.props.commCfgs;
        commCfgs.map((commCfg: ICommCfg) => commCfgNameMap[commCfg.id] = commCfg.groupName);

        processes.map((eachProcess: IProcess) => {
            const value = '' + eachProcess.clusterId;
            const groupName = commCfgNameMap[eachProcess.clusterId];
            const clusterValue = clusterMap[value];
            if (!(clusterValue == null || clusterValue === undefined)) {
                return;
            }

            clusterFilterDatas.push({
                text: groupName,
                value,
            });
            clusterMap[value] = true;
        });

        const columns = [{title: 'ID', dataIndex: 'id', key: 'id'},
            {title: '设备名称', dataIndex: 'processName', key: 'processName'},
            {title: '设备IP', dataIndex: 'processIp', key: 'processIp'},
            {
                title: '所属集群', key: 'clusterId',
                filters: clusterFilterDatas,
                filteredValue: this.props.filter4Cluster,
                onFilter: (value: string, record: IProcess) => (record.clusterId + '') === value,
                render: (text: string, record: IProcess) => {
                    return commCfgNameMap[record.clusterId];
                },
            },
            {title: '所属平台', dataIndex: 'platName', key: 'platName'},
            {
                title: '服务器类型', key: 'processType',
                render: (text: string, record: IProcess) => {
                    let processTypeStr = '未知';
                    if (record.processType === WORLD) {
                        processTypeStr = '游戏服';
                    } else if (record.processType === HOME) {
                        processTypeStr = '玩家服';
                    } else if (record.processType === CHAT) {
                        processTypeStr = '聊天服';
                    } else if (record.processType === PUBLIC) {
                        processTypeStr = '公共服';
                    } else if (record.processType === GATE) {
                        processTypeStr = '网关服';
                    } else if (record.processType === BATTLE_LOGIC) {
                        processTypeStr = '战斗计算服';
                    }
                    return processTypeStr;
                },
                filters: [{
                    text: '游戏服',
                    value: '' + WORLD,
                }, {
                    text: '玩家服',
                    value: '' + HOME,
                }, {
                    text: '聊天服',
                    value: '' + CHAT,
                }, {
                    text: '公共服',
                    value: '' + PUBLIC,
                }, {
                    text: '网关服',
                    value: '' + GATE,
                }, {
                    text: '战斗计算服',
                    value: '' + BATTLE_LOGIC,
                }],
                filteredValue: this.props.filter4ProcessType,
                onFilter: (value: string, record: IProcess) => (record.processType + '') === value,
            },
            {
                title: '对外Socket地址', key: 'processTcpAddr',
                render: (text: string, record: IProcess) => {
                    let tcpAddrStr = '*';
                    if (record.processType === WORLD || record.processType === GATE) {
                        tcpAddrStr = '' + record.processTcpAddr;
                    }
                    return tcpAddrStr;
                },
            },
            {
                title: '对外Web地址', key: 'processWebAddr',
                render: (text: string, record: IProcess) => {
                    let webAddrStr = '*';
                    if (record.processType === HOME) {
                        webAddrStr = '' + record.processWebAddr;
                    }
                    return webAddrStr;
                },
            },
            {
                title: '起始长连接端口', key: 'tcpPort',
                render: (text: string, record: IProcess) => {
                    let tcpPortStr = '*';
                    if (record.processType === WORLD || record.processType === GATE) {
                        tcpPortStr = '' + record.tcpPort;
                    }
                    return tcpPortStr;
                },
            },
            {
                title: '起始Http端口', dataIndex: 'httpPort', key: 'httpPort',
                render: (text: string, record: IProcess) => {
                    let webPortStr = '*';
                    if (record.processType === HOME) {
                        webPortStr = '' + record.httpPort;
                    }
                    return webPortStr;
                },
            },
            {
                title: 'seed端口', dataIndex: 'seedPort', key: 'seedPort',
                render: (text: string, record: IProcess) => {
                    const seedPortStr = '' + record.seedPort;
                    return seedPortStr;
                },
            },
            {title: '分区ID', dataIndex: 'kafkaPId', key: 'kafkaPId'},
            {title: '进程上限', dataIndex: 'processNum', key: 'processNum'},
            {title: '种子节点', dataIndex: 'seedNode', key: 'seedNode'},
            {
                title: '操作', key: 'action',
                render: (text: string, record: IProcess) => (
                    <div>
                        <ButtonGroup>
                            <Button type="primary" onClick={() => {
                                this.chgServer(CHG_GAME_SERVER + '/' + record.id);
                            }}>修改</Button>
                            <Button type="danger" onClick={() => {
                                this.delConfirm(record.processIp, record.processType);
                            }}>删除</Button>
                        </ButtonGroup>
                    </div>
                ),
            }];

        return (
            <div>
                <Row>
                    <Col span={24}>
                        <Button type="primary" onClick={this.switch2Add}>添加新设备配置</Button>
                    </Col>
                </Row>
                <Row>
                    <Col span={24}>
                        <Table rowKey="id" pagination={false} columns={columns} onChange={this.onTableChange}
                               dataSource={processes}
                               size="small"/>
                    </Col>
                </Row>
            </div>
        );
    }

}

export default connect(mapStateToProps, mapDispatchToProps)(ProcessMgr);