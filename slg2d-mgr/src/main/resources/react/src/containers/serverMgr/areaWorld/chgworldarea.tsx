import * as React from 'react';
import { ChangeEvent } from 'react';
import { connect } from 'react-redux';
import { match } from 'react-router';
import { bindActionCreators, Dispatch } from 'redux';
import moment from 'moment';
import { Button, Col, DatePicker, Form, Input, Radio, Row, Select, TimePicker } from 'antd';
import { History } from 'history';

import { chgArea, createOrModifyAreaWorld } from '../../../sagas/serverMgr/areaWorldMgr';
import { ICombineState } from '../../../redux/reducer';
import { WORLD_AREA } from '../../../const/funcType';
import { IMarkCreateModifyAreaWorld, IWorldArea } from '../../../redux/modules/serverMgr/areaWorld';
import { IReqAction } from '../../../sagas/ibaseaction';
import { RadioChangeEvent } from 'antd/lib/radio';
import { queryCommCfgDetail, QueryCommCfgDetailData } from '../../../sagas/serverMgr/commCfgMgr';
import { ICommCfg } from '../../../redux/modules/serverMgr/commCfg';

const FormItem = Form.Item;
const RadioGroup = Radio.Group;
const Option = Select.Option;

interface IChgGameAreaParam {
    aid: string;
}

interface IStateProps {
    history: History;
    match: match<IChgGameAreaParam>;
    areas: IWorldArea[];
    commCfgs: ICommCfg[];
    atCreatingOrModifying: boolean;
}

interface IDispatchProps {
    createOrModifyAreaWorld(atCreatingOrModifying: boolean): IMarkCreateModifyAreaWorld;

    chgArea(data: {}): IReqAction<{}>;

    queryCommCfgDetail(commCfgId: number): IReqAction<QueryCommCfgDetailData>;
}

type IAppProps = IStateProps & IDispatchProps;

function mapStateToProps(state: ICombineState) {
    return {
        areas: state.serverMgr.areaWorld.areas,
        commCfgs: state.serverMgr.commCfg.commCfgs,
        atCreatingOrModifying: state.serverMgr.areaWorld.atCreatingOrModifying,
    };
}

const mapDispatchToProps = (dispatch: Dispatch<{}>) => {
    return bindActionCreators({
        createOrModifyAreaWorld,
        chgArea,
        queryCommCfgDetail,
    }, dispatch);
};

interface IState {
    id: number;
    areaNo: number;
    payKey: string;
    opgameId: number;
    loginKey: string;
    areaState: number;
    pltAreaNo: number;
    gameAreaName: string;
    giftKey: string;
    logTime: moment.Moment;
    clusterId: number;
}

class ChgGameArea extends React.Component<IAppProps, IState> {

    constructor(props: IAppProps) {
        super(props);

        const targetAreaId = parseInt(this.props.match.params.aid, 10);
        let targetArea = null;
        for (const area of this.props.areas) {
            if (area.id === targetAreaId) {
                targetArea = area;
            }
        }

        if (!(targetArea == null || targetArea === undefined)) {
            console.info(targetArea.openAreaDate);
            this.state = {
                id: targetArea.id,
                areaNo: targetArea.areaNo,
                payKey: targetArea.payKey,
                opgameId: targetArea.opgameId,
                loginKey: targetArea.loginKey,
                areaState: targetArea.areaState,
                pltAreaNo: targetArea.pltAreaNo,
                gameAreaName: targetArea.gameAreaName,
                giftKey: targetArea.giftKey,
                logTime: moment(new Date(targetArea.openAreaDate * 1000)),
                clusterId: targetArea.clusterId,
            };
        } else {
            this.state = {
                id: 0,
                areaNo: 0,
                payKey: '',
                opgameId: 0,
                loginKey: '',
                areaState: 0,
                pltAreaNo: 0,
                gameAreaName: '',
                giftKey: '',
                logTime: moment(),
                clusterId: 0,
            };
        }

        this.props.createOrModifyAreaWorld(true);
    }

    componentWillReceiveProps(nextProps: IAppProps) {
        if (!nextProps.atCreatingOrModifying && this.props.atCreatingOrModifying) {
            // 切换到主界面
            this.props.history.push(WORLD_AREA);
        }
    }

    cancelAdd = () => {
        this.props.createOrModifyAreaWorld(false);
    };

    updatePltAreaNo = (event: ChangeEvent<HTMLInputElement>) => {
        const pltAreaNo = parseInt(event.target.value, 10);
        this.setState({
            pltAreaNo,
        });
    };

    updateAreaNo = (event: ChangeEvent<HTMLInputElement>) => {
        const areaNo = parseInt(event.target.value, 10);
        this.setState({
            areaNo,
        });
    };

    updateAreaName = (event: ChangeEvent<HTMLInputElement>) => {
        const gameAreaName = event.target.value;
        this.setState({
            gameAreaName,
        });
    };

    updatePayKey = (event: ChangeEvent<HTMLInputElement>) => {
        const payKey = event.target.value;
        this.setState({
            payKey,
        });
    };

    updateOpGameId = (event: ChangeEvent<HTMLInputElement>) => {
        const opgameId = parseInt(event.target.value, 10);
        this.setState({
            opgameId,
        });
    };

    onChangeDate = (date: moment.Moment) => {

        if (date === null) {
            return;
        }

        const currentMoment = moment(this.state.logTime);
        currentMoment.year(date.year());
        currentMoment.month(date.month());
        currentMoment.date(date.date());

        this.setState({
            logTime: currentMoment,
        });
    };

    onChangeTime = (time: moment.Moment) => {

        if (time === null) {
            return;
        }

        const currentMoment = moment(this.state.logTime);
        currentMoment.hour(time.hour());
        currentMoment.minute(time.minute());
        currentMoment.second(time.second());

        this.setState({
            logTime: currentMoment,
        });
    };

    updateAreaState = (event: RadioChangeEvent) => {
        const areaState = parseInt(event.target.value, 10);
        this.setState({
            areaState,
        });
    };

    updateLoginKey = (event: ChangeEvent<HTMLInputElement>) => {
        const loginKey = event.target.value;
        this.setState({
            loginKey,
        });
    };

    updateGiftKey = (event: ChangeEvent<HTMLInputElement>) => {
        const giftKey = event.target.value;
        this.setState({
            giftKey,
        });
    };

    // 集群
    updateCluster = (event: string) => {
        //  重新刷新selectPlatForm
        const clusterId = parseInt(event, 10);
        this.setState({
            clusterId,
        });
    };

    handleSubmit = () => {

        const startTime = this.state.logTime.unix();

        const data = {
            id: this.state.id,
            areaNo: this.state.areaNo,
            payKey: this.state.payKey,
            opgameId: this.state.opgameId,
            areaState: this.state.areaState,
            pltAreaNo: this.state.pltAreaNo,
            gameAreaName: this.state.gameAreaName,
            giftKey: this.state.giftKey,
            loginKey: this.state.loginKey,
            openAreaDate: startTime,
            clusterId: this.state.clusterId,
        };

        this.props.chgArea(data);
    };

    render() {
        const formItemLayout = {
            labelCol: {span: 4},
            wrapperCol: {span: 10},
        };
        const tailFormItemLayout = {
            wrapperCol: {
                offset: 4,
                span: 10,
            },
        };

        const allClusters = this.props.commCfgs;
        const clusterList = allClusters.map((commcfg: ICommCfg) => {
            return (<Option value={'' + commcfg.id} key={commcfg.id}>{commcfg.groupName}</Option>);
        });

        return (
            <Row>
                <Col span={24}>
                    <Form>
                        <FormItem {...formItemLayout} label="区域编号">
                            <Input type="number" value={this.state.pltAreaNo} onChange={this.updatePltAreaNo}/>
                        </FormItem>

                        <FormItem {...formItemLayout} label="区号">
                            <Input type="number" value={this.state.areaNo} onChange={this.updateAreaNo}/>
                        </FormItem>

                        <FormItem {...formItemLayout} label="区名">
                            <Input type="text" value={this.state.gameAreaName} onChange={this.updateAreaName}/>
                        </FormItem>

                        <FormItem {...formItemLayout} label="区状态">
                            <RadioGroup onChange={this.updateAreaState} value={this.state.areaState}>
                                <Radio value={2}>开启</Radio>
                                <Radio value={1}>维护</Radio>
                            </RadioGroup>
                        </FormItem>

                        <FormItem {...formItemLayout} label="开服日期">
                            <DatePicker defaultValue={this.state.logTime} onChange={this.onChangeDate}/>
                            <TimePicker defaultValue={this.state.logTime} onChange={this.onChangeTime}/>
                        </FormItem>

                        <FormItem {...formItemLayout} label="混服组ID">
                            <Input type="number" value={this.state.opgameId} onChange={this.updateOpGameId}/>
                        </FormItem>

                        <FormItem {...formItemLayout} label="支付Key">
                            <Input type="text" value={this.state.payKey} onChange={this.updatePayKey}/>
                        </FormItem>

                        <FormItem {...formItemLayout} label="登陆Key">
                            <Input type="text" value={this.state.loginKey} onChange={this.updateLoginKey}/>
                        </FormItem>

                        <FormItem {...formItemLayout} label="礼包码">
                            <Input type="text" value={this.state.giftKey} onChange={this.updateGiftKey}/>
                        </FormItem>

                        <FormItem {...formItemLayout} label="集群">
                            <Select value={'' + this.state.clusterId} onChange={this.updateCluster}>
                                {clusterList}
                            </Select>
                        </FormItem>

                        <FormItem {...tailFormItemLayout}>
                            <Button type="primary" onClick={this.handleSubmit}>修改</Button>
                            <Button type="primary" onClick={this.cancelAdd}>返回</Button>
                        </FormItem>
                    </Form>
                </Col>
            </Row>
        );
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(ChgGameArea);