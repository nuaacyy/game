import * as React from 'react';
import { ChangeEvent } from 'react';
import { Button, Col, Form, Input, Row, Select } from 'antd';
import { BATTLE_LOGIC, CHAT, WORLD, GATE, HOME, PUBLIC } from './process';
import { IProcess } from '../../../redux/modules/serverMgr/process';
import { ICommCfg } from '../../../redux/modules/serverMgr/commCfg';
import { IPlatform } from '../../../redux/modules/serverMgr/platform';
import { SelectValue } from 'antd/lib/select';
import { IReqAction } from '../../../sagas/ibaseaction';

const FormItem = Form.Item;
const Option = Select.Option;

interface IUiStateProps {
    servers: IProcess[];
    commCfgs: ICommCfg[];
    platforms: IPlatform[];
    targetServer: IProcess;
    chgMark: boolean;
}

interface IUiDispatchProps {
    addOrChgProcess(data: {}): IReqAction<{}>;

    cancelSubmit(): void;
}

type IUiAppProps = IUiStateProps & IUiDispatchProps;

interface IState {
    id: number;
    platId: number;
    httpPort: number;
    tcpPort: number;
    seedPort: number;
    processNum: number;
    processIp: string;
    processType: number;
    processName: string;
    processTcpAddr: string;
    processWebAddr: string;
    kafkaPId: number;
    clusterId: number;
    seedNode: number;
}

export class AddOrChgProcessCmp extends React.Component<IUiAppProps, IState> {

    constructor(props: IUiAppProps) {
        super(props);

        const targetServer = props.targetServer;
        this.state = {
            id: targetServer.id,
            platId: targetServer.platId,
            tcpPort: targetServer.tcpPort,
            httpPort: targetServer.httpPort,
            seedPort: targetServer.seedPort,
            processIp: targetServer.processIp,
            processNum: targetServer.processNum,
            processName: targetServer.processName,
            processType: targetServer.processType,
            processTcpAddr: targetServer.processTcpAddr,
            processWebAddr: targetServer.processWebAddr,
            kafkaPId: targetServer.kafkaPId,
            clusterId: targetServer.clusterId,
            seedNode: targetServer.seedNode,
        };
    }

    updateServerIp = (event: ChangeEvent<HTMLInputElement>) => {
        const processIp = event.target.value;
        this.setState({
            processIp,
        });
    };

    updateServerName = (event: ChangeEvent<HTMLInputElement>) => {
        const processName = event.target.value;
        this.setState({
            processName,
        });
    };

    updateHttpPort = (event: ChangeEvent<HTMLInputElement>) => {
        const httpPort = parseInt(event.target.value, 10);
        this.setState({
            httpPort,
        });
    };

    updateTcpPort = (event: ChangeEvent<HTMLInputElement>) => {
        const tcpPort = parseInt(event.target.value, 10);
        this.setState({
            tcpPort,
        });
    };

    updateSeedPort = (event: ChangeEvent<HTMLInputElement>) => {
        const seedPort = parseInt(event.target.value, 10);
        this.setState({
            seedPort,
        });
    };

    updateKafkaPId = (event: ChangeEvent<HTMLInputElement>) => {
        const kafkaPId = parseInt(event.target.value, 10);
        this.setState({
            kafkaPId,
        });
    };

    updatePlatId = (event: string) => {
        const value = parseInt(event, 10);
        this.setState({
            platId: value,
        });
    };

    updateProcessNum = (event: ChangeEvent<HTMLInputElement>) => {
        const processNum = parseInt(event.target.value, 10);
        this.setState({
            processNum,
        });
    };

    updateSeedNode = (event: ChangeEvent<HTMLInputElement>) => {
        const seedNode = parseInt(event.target.value, 10);
        this.setState({
            seedNode,
        });
    };

    updateTcpAddr = (event: ChangeEvent<HTMLInputElement>) => {
        const processTcpAddr = event.target.value;
        this.setState({
            processTcpAddr,
        });
    };

    updateWebAddr = (event: ChangeEvent<HTMLInputElement>) => {
        const processWebAddr = event.target.value;
        this.setState({
            processWebAddr,
        });
    };

    updateProcessType = (event: SelectValue) => {
        const processType = parseInt(event as string, 10);
        this.setState({
            processType,
        });
    };

    updateClusterId = (value: SelectValue) => {
        const clusterId = parseInt(value as string, 10);
        this.setState({
            clusterId,
        });
    };

    cancelSubmit = () => {
        this.props.cancelSubmit();
    };

    handleSubmit = () => {
        const data = this.state;

        this.props.addOrChgProcess(data);
    };

    render() {
        const formItemLayout = {
            labelCol: {span: 6},
            wrapperCol: {span: 14},
        };
        const formWrapperCol = {
            span: 12,
            offset: 6,
        };

        const platforms = this.props.platforms;
        const platFormList = platforms.map((platform: IPlatform) => {
            return (<Option value={'' + platform.id} key={platform.id}>{platform.platformName}</Option>);
        });

        const commCfgs = this.props.commCfgs;
        const commCfgList = commCfgs.map((commCfg: ICommCfg) => {
            return (<Option value={'' + commCfg.id} key={commCfg.id}>{commCfg.groupName}</Option>);
        });

        let needWeb: boolean = false;
        let needSocketHost: boolean = false;
        let needSocketPort: boolean = false;
        let needKafka: boolean = true;
        let isSeedNode: boolean = true;
        if (this.state.processType === WORLD) {
            needSocketHost = true;
            needSocketPort = true;
        } else if (this.state.processType === HOME) {
            needWeb = true;
        } else if (this.state.processType === CHAT) {
            //
        } else if (this.state.processType === PUBLIC) {
            //
        } else if (this.state.processType === GATE) {
            needSocketHost = true;
            needSocketPort = true;
        } else if (this.state.processType === BATTLE_LOGIC) {
            needKafka = false;
            isSeedNode = false;
        }
        return (
            <Row>
                <Col span={16}>
                    <Form>
                        <FormItem {...formItemLayout} label="服务器类型">
                            <Select value={'' + this.state.processType} style={{width: 120}}
                                    onChange={this.updateProcessType} disabled={this.props.chgMark}>
                                <Option value={'' + WORLD}>游戏服</Option>
                                <Option value={'' + HOME}>玩家服</Option>
                                <Option value={'' + CHAT}>聊天服</Option>
                                <Option value={'' + PUBLIC}>公共服</Option>
                                <Option value={'' + GATE}>网关服</Option>
                                <Option value={'' + BATTLE_LOGIC}>战斗计算服</Option>
                            </Select>
                        </FormItem>

                        <FormItem {...formItemLayout} label="集群">
                            <Select value={'' + this.state.clusterId} onChange={this.updateClusterId}>
                                {commCfgList}
                            </Select>
                        </FormItem>

                        <FormItem {...formItemLayout} label="设备名称">
                            <Input type="text" value={this.state.processName}
                                   onChange={this.updateServerName}/>
                        </FormItem>

                        <FormItem {...formItemLayout} label="设备IP">
                            <Input type="text" value={this.state.processIp}
                                   onChange={this.updateServerIp} disabled={this.props.chgMark}/>
                        </FormItem>

                        {needSocketHost ? (
                            <div>
                                <FormItem {...formItemLayout} label="对外Socket地址">
                                    <Input type="text" value={this.state.processTcpAddr}
                                           onChange={this.updateTcpAddr}/>
                                </FormItem>
                            </div>
                        ) : (<div/>)}

                        {needWeb ? (
                            <div>
                                <FormItem {...formItemLayout} label="对外Http地址">
                                    <Input type="text" value={this.state.processWebAddr}
                                           onChange={this.updateWebAddr}/>
                                </FormItem>
                                <FormItem {...formItemLayout} label="起始Http端口">
                                    <Input type="number" value={this.state.httpPort} onChange={this.updateHttpPort}/>
                                </FormItem>
                            </div>
                        ) : (<div/>)}

                        {needSocketPort ? (
                            <div>
                                <FormItem {...formItemLayout} label="对外Socket端口">
                                    <Input type="number" value={this.state.tcpPort} onChange={this.updateTcpPort}/>
                                </FormItem>
                            </div>
                        ) : (<div/>)}

                        <FormItem {...formItemLayout} label="seed端口">
                            <Input type="number" value={this.state.seedPort} onChange={this.updateSeedPort}/>
                        </FormItem>

                        {needKafka ? (<FormItem {...formItemLayout} label="设备所属kafkaPId">
                            <Input placeholder="必须是10的倍数且不能为0" type="number" value={this.state.kafkaPId}
                                   onChange={this.updateKafkaPId}/>
                        </FormItem>) : (<div/>)}

                        <FormItem {...formItemLayout} label="进程上限">
                            <Input placeholder="大于0小于等于10" type="number" value={this.state.processNum}
                                   onChange={this.updateProcessNum}/>
                        </FormItem>

                        <FormItem {...formItemLayout} label="所属平台">
                            <Select value={'' + this.state.platId} onChange={this.updatePlatId}>
                                {platFormList}
                            </Select>
                        </FormItem>

                        {isSeedNode ? (<FormItem {...formItemLayout} label="种子节点">
                            <Input placeholder="0" type="number" value={this.state.seedNode}
                                   onChange={this.updateSeedNode}/>
                        </FormItem>) : (<div/>)}

                        <FormItem wrapperCol={formWrapperCol}>
                            <Button type="primary" onClick={this.handleSubmit}>保存</Button>
                            <Button onClick={this.cancelSubmit}>取消</Button>
                        </FormItem>

                    </Form>

                </Col>
            </Row>
        );
    }
}