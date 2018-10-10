import * as React from 'react';
import { ChangeEvent } from 'react';
import { connect } from 'react-redux';
import { match } from 'react-router';
import { bindActionCreators, Dispatch } from 'redux';
import { Button, Col, Form, Input, Row } from 'antd';
import { History } from 'history';

import { chgCommCfg, markCreateOrModifyCommonCfg } from '../../../sagas/serverMgr/commCfgMgr';
import { ICombineState } from '../../../redux/reducer';
import { COMM_CFG_MGR } from '../../../const/funcType';
import { ICommCfg, IMarkCreateModifyCommonCfg } from '../../../redux/modules/serverMgr/commCfg';
import { IReqAction } from '../../../sagas/ibaseaction';

const FormItem = Form.Item;

interface IChgCommCfgParam {
    commCfgId: string;
}

interface IStateProps {
    history: History;
    match: match<IChgCommCfgParam>;
    commCfgs: ICommCfg[];
    atCreatingOrModifying: boolean;
}

interface IDispatchProps {
    markCreateOrModifyCommonCfg(atCreatingOrModifying: boolean): IMarkCreateModifyCommonCfg;

    chgCommCfg(data: {}): IReqAction<{}>;
}

type IAppProps = IStateProps & IDispatchProps;

function mapStateToProps(state: ICombineState) {
    return {
        commCfgs: state.serverMgr.commCfg.commCfgs,
        atCreatingOrModifying: state.serverMgr.commCfg.atCreatingOrModifying,
    };
}

const mapDispatchToProps = (dispatch: Dispatch<{}>) => {
    return bindActionCreators({
        markCreateOrModifyCommonCfg,
        chgCommCfg,
    }, dispatch);
};

interface IState {
    id: number;
    groupName: string;
    seedNodes: string;
    kafkaAddrs: string;
    dcLogTopic: string;
}

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

class ChgCommCfg extends React.Component<IAppProps, IState> {

    constructor(props: IAppProps) {
        super(props);

        const commCfgId = parseInt(this.props.match.params.commCfgId, 10);
        let targetCfg = null;
        for (const eachCfg of this.props.commCfgs) {
            if (eachCfg.id === commCfgId) {
                targetCfg = eachCfg;
                break;
            }
        }

        if (!(targetCfg == null || targetCfg === undefined)) {
            this.state = {
                id: targetCfg.id,
                groupName: targetCfg.groupName,
                seedNodes: targetCfg.seedNodes,
                kafkaAddrs: targetCfg.kafkaAddrs,
                dcLogTopic: targetCfg.dcLogTopic,
            };
        } else {
            this.state = {
                id: 0,
                groupName: '',
                seedNodes: '',
                kafkaAddrs: '',
                dcLogTopic: '',
            };
        }

        this.props.markCreateOrModifyCommonCfg(true);
    }

    componentWillReceiveProps(nextProps: IAppProps) {
        if (!nextProps.atCreatingOrModifying && this.props.atCreatingOrModifying) {
            this.props.history.push(COMM_CFG_MGR);
        }
    }

    cancelChange = () => {
        this.props.markCreateOrModifyCommonCfg(false);
    };

    handleSubmit = () => {
        const data = {
            id: this.state.id,
            groupName: this.state.groupName,
            seedNodes: this.state.seedNodes,
            kafkaAddrs: this.state.kafkaAddrs,
            dcLogTopic: this.state.dcLogTopic,
        };

        this.props.chgCommCfg(data);
    };

    updateGroupName = (event: ChangeEvent<HTMLInputElement>) => {
        const groupName = event.target.value;
        this.setState({
            groupName,
        });
    };

    updateSeedNodes = (event: ChangeEvent<HTMLInputElement>) => {
        const seedNodes = event.target.value;
        this.setState({
            seedNodes,
        });
    };

    updateKafkaAddrs = (event: ChangeEvent<HTMLInputElement>) => {
        const kafkaAddrs = event.target.value;
        this.setState({
            kafkaAddrs,
        });
    };

    updateDcLogTopic = (event: ChangeEvent<HTMLInputElement>) => {
        const dcLogTopic = event.target.value;
        this.setState({
            dcLogTopic,
        });
    };

    render() {

        return (
            <Row>
                <Col span={24}>
                    <Form>
                        <FormItem {...formItemLayout} label="集群名">
                            <Input value={this.state.groupName} onChange={this.updateGroupName}/>
                        </FormItem>
                        <FormItem {...formItemLayout} label="SeedNodes">
                            <Input value={this.state.seedNodes} onChange={this.updateSeedNodes}/>
                        </FormItem>
                        <FormItem {...formItemLayout} label="Kafka节点">
                            <Input value={this.state.kafkaAddrs} onChange={this.updateKafkaAddrs}/>
                        </FormItem>
                        <FormItem {...formItemLayout} label="日志Topic">
                            <Input value={this.state.dcLogTopic} onChange={this.updateDcLogTopic}/>
                        </FormItem>

                        <FormItem {...tailFormItemLayout}>
                            <Button type="primary" onClick={this.handleSubmit}>修改</Button>
                            <Button type="primary" onClick={this.cancelChange}>取消</Button>
                        </FormItem>
                    </Form>
                </Col>
            </Row>
        );
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(ChgCommCfg);
