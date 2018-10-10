import * as React from 'react';
import { ChangeEvent } from 'react';
import { connect } from 'react-redux';

import { bindActionCreators, Dispatch } from 'redux';
import moment from 'moment';
import { Button, Col, DatePicker, Form, Input, Radio, Row, Select, TimePicker } from 'antd';
import { History } from 'history';

import { addArea, createOrModifyAreaWorld } from '../../../sagas/serverMgr/areaWorldMgr';
import { ICombineState } from '../../../redux/reducer';
import { WORLD_AREA } from '../../../const/funcType';
import { IReqAction } from '../../../sagas/ibaseaction';
import { RadioChangeEvent } from 'antd/lib/radio';
import { queryCommCfgDetail, QueryCommCfgDetailData } from '../../../sagas/serverMgr/commCfgMgr';
import { ICommCfg } from '../../../redux/modules/serverMgr/commCfg';
import { IMarkCreateModifyAreaWorld } from '../../../redux/modules/serverMgr/areaWorld';

const FormItem = Form.Item;
const RadioGroup = Radio.Group;
const Option = Select.Option;

interface IStateProps {
    history: History;
    commCfgs: ICommCfg[];
    atCreatingOrModifying: boolean;
}

interface IDispatchProps {
    createOrModifyAreaWorld(atCreatingOrModifying: boolean): IMarkCreateModifyAreaWorld;

    addArea(data: {}): IReqAction<{}>;

    queryCommCfgDetail(commCfgId: number): IReqAction<QueryCommCfgDetailData>;
}

type IAppProps = IStateProps & IDispatchProps;

function mapStateToProps(state: ICombineState) {
    return {
        commCfgs: state.serverMgr.commCfg.commCfgs,
        atCreatingOrModifying: state.serverMgr.areaWorld.atCreatingOrModifying
    };
}

const mapDispatchToProps = (dispatch: Dispatch<{}>) => {
    return bindActionCreators({
        createOrModifyAreaWorld,
        addArea,
        queryCommCfgDetail,
    }, dispatch);
};

interface IState {
    payKey: string;
    areaNo: number;
    loginKey: string;
    opgameId: number;
    areaState: number;
    pltAreaNo: number;
    gameAreaName: string;
    giftKey: string;
    openAreaDate: moment.Moment;
    clusterId: number;
}

class AddArea extends React.Component<IAppProps, IState> {

    constructor(props: IAppProps) {
        super(props);

        let clusterId = 1;
        if (this.props.commCfgs[0] !== undefined) {
            clusterId = this.props.commCfgs[0].id;
        }

        let now = moment();
        this.state = {
            payKey: '',
            areaNo: 0,
            loginKey: '',
            opgameId: 0,
            areaState: 1,
            pltAreaNo: 0,
            gameAreaName: '',
            giftKey: '',
            openAreaDate: now,
            clusterId,
        };

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

    onChangeDate = (date: moment.Moment) => {
        if (date === null) {
            return;
        }

        const currentMoment = moment(this.state.openAreaDate);
        currentMoment.year(date.year());
        currentMoment.month(date.month());
        currentMoment.date(date.date());

        this.setState({
            openAreaDate: currentMoment,
        });
    };

    onChangeTime = (time: moment.Moment) => {
        if (time === null) {
            return;
        }

        const currentMoment = moment(this.state.openAreaDate);
        currentMoment.hour(time.hour());
        currentMoment.minute(time.minute());
        currentMoment.second(time.second());

        this.setState({
            openAreaDate: currentMoment,
        });
    };

    updateAreaName = (event: ChangeEvent<HTMLInputElement>) => {
        const gameAreaName = event.target.value;
        this.setState({
            gameAreaName,
        });
    };

    updateOpGameId = (event: ChangeEvent<HTMLInputElement>) => {
        const opgameId = parseInt(event.target.value, 10);
        this.setState({
            opgameId,
        });
    };

    updatePayKey = (event: ChangeEvent<HTMLInputElement>) => {
        const payKey = event.target.value;
        this.setState({
            payKey,
        });
    };

    updateLoginKey = (event: ChangeEvent<HTMLInputElement>) => {
        const loginKey = event.target.value;
        this.setState({
            loginKey,
        });
    };

    updateAreaState = (event: RadioChangeEvent) => {
        const areaState = parseInt(event.target.value, 10);
        this.setState({
            areaState,
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
        // 此处是把时间转换为秒数
        const startTime = this.state.openAreaDate.unix();
        const data = {
            areaNo: this.state.areaNo,
            payKey: this.state.payKey,
            opgameId: this.state.opgameId,
            loginKey: this.state.loginKey,
            areaState: this.state.areaState,
            pltAreaNo: this.state.pltAreaNo,
            gameAreaName: this.state.gameAreaName,
            giftKey: this.state.giftKey,
            openAreaDate: startTime,
            whichAction: 0,
            clusterId: this.state.clusterId,
        };
        this.props.addArea(data);
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

        moment().format();

        const allClusters = this.props.commCfgs;
        const clusterList = allClusters.map((commcfg: ICommCfg) => {
            return (<Option value={'' + commcfg.id} key={commcfg.id}>{commcfg.groupName}</Option>);
        });

        return (
            <Row>
                <Col span={24}>
                    <Row>
                        <Col span={24}>
                            <Form>
                                <FormItem {...formItemLayout} label="区域编号">
                                    <Input type="number" value={this.state.pltAreaNo}
                                           onChange={this.updatePltAreaNo}/>
                                </FormItem>

                                <FormItem {...formItemLayout} label="区号">
                                    <Input type="number" value={this.state.areaNo} onChange={this.updateAreaNo}/>
                                </FormItem>

                                <FormItem {...formItemLayout} label="区名">
                                    <Input type="text" value={this.state.gameAreaName}
                                           onChange={this.updateAreaName}/>
                                </FormItem>

                                <FormItem {...formItemLayout} label="区状态">
                                    <RadioGroup onChange={this.updateAreaState} value={this.state.areaState}>
                                        <Radio value={2}>开启</Radio>
                                        <Radio value={1}>维护</Radio>
                                    </RadioGroup>
                                </FormItem>

                                <FormItem {...formItemLayout} label="开服日期">
                                    <DatePicker defaultValue={this.state.openAreaDate} onChange={this.onChangeDate}/>
                                    <TimePicker defaultValue={this.state.openAreaDate} onChange={this.onChangeTime}/>
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
                                    <Button type="primary" onClick={this.handleSubmit}>添加</Button>
                                    <Button type="primary" onClick={this.cancelAdd}>返回</Button>
                                </FormItem>
                            </Form>
                        </Col>
                    </Row>
                </Col>
            </Row>
        );
    }

}

export default connect(mapStateToProps, mapDispatchToProps)(AddArea);