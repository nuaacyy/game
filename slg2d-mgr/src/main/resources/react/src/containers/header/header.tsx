import * as React from 'react';

import { connect } from 'react-redux';
import { bindActionCreators, Dispatch } from 'redux';
import { Button, Col, Form, Row, Select } from 'antd';
import { selectArea } from '../../redux/modules/selectArea';
import { ICombineState } from '../../redux/reducer';
import { showSpecFuncs } from '../../redux/modules/main';
import { GAME_DEV, GAME_MGR, GM_MGR, SERVER_MGR, SERVER_MONITOR } from '../../const/funcType';
import { IWorldArea } from '../../redux/modules/serverMgr/areaWorld';
import { IProcess } from '../../redux/modules/serverMgr/process';
import { IPlatform } from '../../redux/modules/serverMgr/platform';

const ButtonGroup = Button.Group;
const FormItem = Form.Item;
const Option = Select.Option;

interface IStateProps {
    dirty: boolean;
    areas: IWorldArea[];
    servers: IProcess[];
    platforms: IPlatform[];
}

interface IDispatchProps {
    showSpecFuncs(funcType: string): {};

    selectArea(areaId: number): {};
}

type IAppProps = IStateProps & IDispatchProps;

function mapStateToProps(state: ICombineState) {
    return {
        dirty: state.serverMgr.areaWorld.dirty,
        areas: state.serverMgr.areaWorld.areas,
        servers: state.serverMgr.process.servers,
        platforms: state.serverMgr.platform.platforms,
    };
}

const mapDispatchToProps = (dispatch: Dispatch<{}>) => {
    return bindActionCreators({
        showSpecFuncs,
        selectArea,
    }, dispatch);
};

interface IState {
    areaId: number;
    platformId: number;
}

class MainHeader extends React.Component<IAppProps, IState> {

    constructor(props: IAppProps) {
        super(props);

        this.state = {
            areaId: 1,
            platformId: 1,
        };
    }

    componentWillReceiveProps(nextProps: IAppProps) {
        if (nextProps.dirty !== this.props.dirty) {
            let platId = 1;
            if (nextProps.platforms[0] !== undefined) {
                platId = nextProps.platforms[0].id;
            }
            this.setState({
                platformId: platId,
            });
            let areaId = 1;
            if (nextProps.areas[0] !== undefined) {
                areaId = nextProps.areas[0].id;
            }

            this.props.selectArea(areaId);

            this.setState({
                areaId,
            });
        }
    }

    updateAreaId = (event: string) => {
        const areaId = parseInt(event, 10);
        this.setState({
            areaId,
        });
        this.props.selectArea(areaId);
    };

    // 输入更新
    updatePlatform = (event: string) => {
        const platformId = parseInt(event, 10);

        // 重新刷新selectPlatForm
        this.setState({
            platformId
        });

        // 1.循环遍历所有的服务器
        const servers = this.props.servers;
        const serverSX: IProcess[] = [];
        servers.map((server: IProcess) => {
            if (server.platId === platformId) {
                serverSX.push(server);
            }
        });

        const areaList: IWorldArea[] = [];
        const areas = this.props.areas;
        areas.filter((area: IWorldArea) => {
            serverSX.map((server: IProcess) => {
                areaList.push(area);
                return true;
            });
            return false;
        });

        if (areaList.length !== 0) {
            this.props.selectArea(areaList[0].id);
        }

    };

    showFunc = (funcType: string): void => {
        this.props.showSpecFuncs(funcType);
    };

    render() {
        const areas = this.props.areas;
        const servers = this.props.servers;
        const platForms = this.props.platforms;

        const serverSX: IProcess[] = [];
        servers.map((server: IProcess) => {
            if (server.platId === this.state.platformId) {
                serverSX.push(server);
            }
        });

        const platFormList = platForms.map((platForm: IPlatform) => {
            return (<Option value={'' + platForm.id} key={platForm.id}>{platForm.platformName}</Option>);
        });

        const areaList: {}[] = [];
        areas.filter((area: IWorldArea) => {
            serverSX.map((server: IProcess) => {
                areaList.push(<Option value={'' + area.id} key={area.id}>{area.gameAreaName}</Option>);
                return true;
            });
            return false;
        });

        return (
            <Row>
                <Col span={12}>
                    <ButtonGroup>
                        <Button onClick={(): void => this.showFunc(GM_MGR)}>Center中心管理</Button>
                        <Button onClick={(): void => this.showFunc(SERVER_MGR)}>部署管理</Button>
                        <Button onClick={(): void => this.showFunc(GAME_MGR)}>客服工具</Button>
                        <Button onClick={(): void => this.showFunc(SERVER_MONITOR)}>监控</Button>
                        <Button onClick={(): void => this.showFunc(GAME_DEV)}>研发工具</Button>
                    </ButtonGroup>
                </Col>

                <Col span={12}>
                    <Form layout="inline">
                        <FormItem label="请选择平台">
                            <Select style={{width: 300}} value={'' + this.state.platformId}
                                    onChange={this.updatePlatform}>
                                {platFormList}
                            </Select>
                        </FormItem>
                        <FormItem label="请选择区">
                            <Select style={{width: 100}} value={'' + this.state.areaId} onChange={this.updateAreaId}>
                                {areaList}
                            </Select>
                        </FormItem>
                    </Form>
                </Col>
            </Row>
        );
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(MainHeader);