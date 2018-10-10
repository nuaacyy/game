import * as React from 'react';
import { connect } from 'react-redux';
import { bindActionCreators, Dispatch } from 'redux';

import { Button, Col, message, Modal, Row, Table, Tabs } from 'antd';
import { History } from 'history';

import { deleteArea, IDelAreaData, markAreaCacheDirty } from '../../../sagas/serverMgr/areaWorldMgr';
import { ICombineState } from '../../../redux/reducer';
import { ADD_WORLD_AREA, CHG_WORLD_AREA } from '../../../const/funcType';
import { IWorldArea } from '../../../redux/modules/serverMgr/areaWorld';
import { IBaseAction, IReqAction } from '../../../sagas/ibaseaction';
import { queryCommCfgDetail, QueryCommCfgDetailData } from '../../../sagas/serverMgr/commCfgMgr';
import { ICommCfg } from '../../../redux/modules/serverMgr/commCfg';

const TabPane = Tabs.TabPane;
const ButtonGroup = Button.Group;

const confirm = Modal.confirm;

// 游戏区状态解析
const areaStatusMap = new Map<number, string>();
areaStatusMap[0] = '关闭';
areaStatusMap[1] = '运行';
areaStatusMap[2] = '无法探测';
areaStatusMap[3] = '未部署';
areaStatusMap[4] = '维护';

interface IStateProps {
    history: History;

    areas: IWorldArea[];

    commCfgs: ICommCfg[];
}

interface IDispatchProps {
    markAreaCacheDirty(): IBaseAction;

    deleteArea(id: number): IReqAction<IDelAreaData>;

    queryCommCfgDetail(commCfgId: number): IReqAction<QueryCommCfgDetailData>;
}

type IAppProps = IStateProps & IDispatchProps;

function mapStateToProps(state: ICombineState) {
    return {
        areas: state.serverMgr.areaWorld.areas,
        commCfgs: state.serverMgr.commCfg.commCfgs,
    };
}

const mapDispatchToProps = (dispatch: Dispatch<{}>) => {
    return bindActionCreators({
        markAreaCacheDirty,
        deleteArea,
        queryCommCfgDetail,
    }, dispatch);
};

interface IState {
    intervalId: number;
    runState: number;
    selectedAreaIds: number[];
}

class WorldAreaMgr extends React.Component<IAppProps, IState> {

    private timerID: number;

    constructor(props: IAppProps) {
        super(props);

        this.state = {
            intervalId: 0,
            runState: 5,
            selectedAreaIds: [],
        };
    }

    componentWillUnmount() {
        window.clearInterval(this.timerID);
    }

    componentDidMount() {
        this.timerID = window.setInterval(
            () => this.tick(),
            5000,
        );
    }

    tick() {
        this.props.markAreaCacheDirty();
    }

    showResult = (successMsg: string, errorMsg: string, currentValue: number, nextValue: number,
                  cantList: number[]) => {
        if (currentValue === 0 && nextValue !== 0) {
            let cantCleanDb = '';
            if (cantList.length > 0) {
                cantCleanDb = '无法执行列表：';
                cantList.map((eachId: number) => {
                    cantCleanDb += '' + eachId + '、';
                });
            }
            if (nextValue === 1) {
                message.success(`${successMsg}，${cantCleanDb}`);
            } else {
                message.error(`${errorMsg}，返回：${nextValue}，${cantCleanDb}`);
            }
        }
    };

    delConfirm = (recordId: number) => {
        confirm({
            title: '删除游戏区配置',
            content: '你确定要删除这条记录？',
            onOk: () => {
                this.props.deleteArea(recordId);
            },
        });
    };

    // 修改区配置
    chgArea = (areaId: number) => {
        const path = CHG_WORLD_AREA + '/' + areaId;
        this.props.history.push(path);
    };

    // 切换tab标签
    updateRunState = (runState: string) => {
        this.setState({
            selectedAreaIds: [], // 每次切换tab，需要清空选择
            runState: parseInt(runState, 10),
        });
    };

    // 切换到添加游戏区配置的页面
    switch2Add = () => {
        console.info('switch2add');
        this.props.history.push(ADD_WORLD_AREA);
    };

    render() {
        const clustersList = this.props.commCfgs;

        const shanxuanList: IWorldArea[] = [];
        const areas = this.props.areas;

        areas.map((eachArea: IWorldArea) => {
            if (this.state.runState !== 5 && eachArea.operationState !== this.state.runState) {
                return;
            }

            shanxuanList.push(eachArea);
        });

        const columns = [
            {title: 'ID', dataIndex: 'id', key: 'id'},
            {title: '编号', dataIndex: 'pltAreaNo', key: 'pltAreaNo'},
            {title: '区号', dataIndex: 'areaNo', key: 'areaNo'},
            {title: '名称', dataIndex: 'gameAreaName', key: 'gameAreaName'},
            {
                title: '集群名称', key: 'clusterId',
                render: (text: string, record: IWorldArea) => {
                    let clusterStr = '未知';
                    clustersList.map((commcfg: ICommCfg) => {
                        if (record.clusterId === commcfg.id) {
                            clusterStr = commcfg.groupName;
                        }
                    });
                    return clusterStr;
                },
            },
            {
                title: '维护模式', key: 'areaState',
                render: (text: string, record: IWorldArea) => {
                    let areaStateStr = '未知';
                    if (record.areaState === 2) {
                        areaStateStr = '开放';
                    } else if (record.areaState === 1) {
                        areaStateStr = '维护';
                    }
                    return areaStateStr;
                },
            },
            {
                title: '运行状态', key: 'operationState',
                render: (text: string, record: IWorldArea) => {
                    let areaoperationStr = areaStatusMap[record.operationState];
                    if (areaoperationStr === null) {
                        areaoperationStr = '';
                    }
                    return areaoperationStr;
                },
            },
            {
                title: '操作', key: 'action',
                render: (text: string, record: IWorldArea) => (
                    <div>
                        <ButtonGroup>
                            <Button type="primary" disabled={record.whichAction !== 0}
                                    onClick={() => {
                                        this.chgArea(record.id);
                                    }}>修改</Button>
                            <Button type="danger" disabled={record.whichAction !== 0} onClick={() => {
                                this.delConfirm(record.id);
                            }}>删除</Button>
                        </ButtonGroup>
                    </div>
                ),
            },
        ];

        const {selectedAreaIds} = this.state;
        const rowSelection = {
            selectedRowKeys: selectedAreaIds,
            onChange: (selectedRowKeys: number[]) => {
                this.setState({
                    selectedAreaIds: selectedRowKeys,
                });
            },
        };

        return (
            <Row>
                <Col span={24}>
                    <Row>
                        <Col span={24}>
                            <Tabs defaultActiveKey={'' + this.state.runState} onChange={this.updateRunState}>
                                <TabPane key={5} tab="全部状态">
                                    <Row>
                                        <Col span={24}>
                                            <Button type="primary" onClick={this.switch2Add}>添加新游戏区</Button>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col span={24}>
                                            <Table rowKey="id" pagination={false}
                                                   rowSelection={rowSelection}
                                                   columns={columns}
                                                   dataSource={shanxuanList} size="small"/>
                                        </Col>
                                    </Row>
                                </TabPane>

                                <TabPane key={3} tab="未部署">
                                    <Row>
                                        <Col span={24}>
                                            <Table rowKey="id" pagination={false}
                                                   rowSelection={rowSelection}
                                                   columns={columns}
                                                   dataSource={shanxuanList} size="small"/>
                                        </Col>
                                    </Row>
                                </TabPane>

                                <TabPane key={0} tab="关闭">
                                    <Row>
                                        <Col span={24}>
                                            <Table rowKey="id" pagination={false}
                                                   rowSelection={rowSelection}
                                                   columns={columns}
                                                   dataSource={shanxuanList} size="small"/>
                                        </Col>
                                    </Row>
                                </TabPane>

                                <TabPane key={4} tab="维护">
                                    <Row>
                                        <Col span={24}>
                                            <Table rowKey="id" pagination={false}
                                                   rowSelection={rowSelection}
                                                   columns={columns}
                                                   dataSource={shanxuanList} size="small"/>
                                        </Col>
                                    </Row>
                                </TabPane>

                                <TabPane key={1} tab="运行">
                                    <Row>
                                        <Col span={24}>
                                            <Table rowKey="id" pagination={false}
                                                   rowSelection={rowSelection}
                                                   columns={columns}
                                                   dataSource={shanxuanList} size="small"/>
                                        </Col>
                                    </Row>
                                </TabPane>

                                <TabPane key={2} tab="无法探测">
                                    <Row>
                                        <Col span={24}>
                                            <Table rowKey="id" pagination={false}
                                                   rowSelection={rowSelection}
                                                   columns={columns}
                                                   dataSource={shanxuanList} size="small"/>
                                        </Col>
                                    </Row>
                                </TabPane>
                            </Tabs>
                        </Col>
                    </Row>

                </Col>
            </Row>
        );
    }

}

export default connect(mapStateToProps, mapDispatchToProps)(WorldAreaMgr);