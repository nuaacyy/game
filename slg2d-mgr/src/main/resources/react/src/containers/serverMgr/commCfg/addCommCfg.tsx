import * as React from 'react';
import { ChangeEvent } from 'react';
import { connect } from 'react-redux';

import { bindActionCreators, Dispatch } from 'redux';
import { Button, Col, Form, Input, Row } from 'antd';
import { addCommCfg, markCreateOrModifyCommonCfg } from '../../../sagas/serverMgr/commCfgMgr';
import { History } from 'history';
import { ICombineState } from '../../../redux/reducer';
import { COMM_CFG_MGR } from '../../../const/funcType';
import { IReqAction } from '../../../sagas/ibaseaction';
import { IMarkCreateModifyCommonCfg } from '../../../redux/modules/serverMgr/commCfg';

const FormItem = Form.Item;

interface IStateProps {
    history: History;
    atCreatingOrModifying: boolean;
}

interface IDispatchProps {
    markCreateOrModifyCommonCfg(atCreatingOrModifying: boolean): IMarkCreateModifyCommonCfg;

    addCommCfg(data: {}): IReqAction<{}>;
}

type IAppProps = IStateProps & IDispatchProps;

function mapStateToProps(state: ICombineState) {
    return {
        atCreatingOrModifying: state.serverMgr.commCfg.atCreatingOrModifying,
    };
}

const mapDispatchToProps = (dispatch: Dispatch<{}>) => {
    return bindActionCreators({
        markCreateOrModifyCommonCfg,
        addCommCfg,
    }, dispatch);
};

interface IState {
    groupName: string;
    seedNodes: string;
    kafkaAddrs: string;
    dcLogTopic: string;
}

class AddCommCfg extends React.Component<IAppProps, IState> {

    constructor(props: IAppProps) {
        super(props);

        this.state = {
            groupName: '',
            seedNodes: '',
            kafkaAddrs: '',
            dcLogTopic: '',
        };

        this.props.markCreateOrModifyCommonCfg(true);
    }

    componentWillReceiveProps(nextProps: IAppProps) {
        if (!nextProps.atCreatingOrModifying && this.props.atCreatingOrModifying) {
            this.props.history.push(COMM_CFG_MGR);
        }
    }

    cancelAdd = () => {
        this.props.markCreateOrModifyCommonCfg(false);
    };

    handleSubmit = () => {
        const data = {
            groupName: this.state.groupName,
            seedNodes: this.state.seedNodes,
            kafkaAddrs: this.state.kafkaAddrs,
            dcLogTopic: this.state.dcLogTopic,
        };

        this.props.addCommCfg(data);

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
                            <Button type="primary" onClick={this.handleSubmit}>添加</Button>
                            <Button type="primary" onClick={this.cancelAdd}>取消</Button>
                        </FormItem>
                    </Form>
                </Col>
            </Row>
        );
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(AddCommCfg);