import * as React from 'react';
import { ChangeEvent } from 'react';
import { connect } from 'react-redux';

import { bindActionCreators, Dispatch } from 'redux';
import { Button, Col, Form, Input, Row, Select } from 'antd';
import { History } from 'history';

import { ICombineState } from '../../../redux/reducer';
import { DATA_SOURCE } from '../../../const/funcType';
import { IReqAction } from '../../../sagas/ibaseaction';
import { addDataSource, markCreateOrModifyDataSource } from '../../../sagas/serverMgr/dataSourceMgr';
import { IDataSource, IMarkCreateModifyDataSource } from '../../../redux/modules/serverMgr/dataSource';
import { SelectValue } from 'antd/lib/select';
import { ICommCfg } from '../../../redux/modules/serverMgr/commCfg';

const FormItem = Form.Item;
const Option = Select.Option;

interface IStateProps {
    history: History;
    atCreatingOrModifying: boolean;
    commCfgs: ICommCfg[];
}

interface IDispatchProps {
    markCreateOrModifyDataSource(atCreatingOrModifying: boolean): IMarkCreateModifyDataSource;
    addDataSource(data: {}): IReqAction<{}>;
}

type IAppProps = IStateProps & IDispatchProps;

function mapStateToProps(state: ICombineState) {
    return {
        atCreatingOrModifying: state.serverMgr.dataSource.atCreatingOrModifying,
        commCfgs: state.serverMgr.commCfg.commCfgs,
    };
}

const mapDispatchToProps = (dispatch: Dispatch<{}>) => {
    return bindActionCreators({
        markCreateOrModifyDataSource,
        addDataSource,
    }, dispatch);
};

class AddDataSource extends React.Component<IAppProps, IDataSource> {

    constructor(props: IAppProps) {
        super(props);

        this.state = {
            showKey: '',
            configName: '',
            shardId: 0,
            socket: '',
            database: '',
            user: '',
            password: '',
            clusterId: 0,
        };

        this.props.markCreateOrModifyDataSource(true);
    }

    componentWillReceiveProps(nextProps: IAppProps) {
        if (!nextProps.atCreatingOrModifying && this.props.atCreatingOrModifying) {
            this.props.history.push(DATA_SOURCE);
        }
    }

    cancelAdd = () => {
        this.props.markCreateOrModifyDataSource(false);
    };

    updateDataSourceshardId = (event: ChangeEvent<HTMLInputElement>) => {
        const shardId = parseInt(event.target.value, 10);
        this.setState({
            shardId,
        });
    };

    updateDataSourceSocket = (event: ChangeEvent<HTMLInputElement>) => {
        const socket = event.target.value;
        this.setState({
            socket,
        });
    };

    updateDataSourceDB = (event: ChangeEvent<HTMLInputElement>) => {
        const database = event.target.value;
        this.setState({
            database,
        });
    };

    updateDataSourceUser = (event: ChangeEvent<HTMLInputElement>) => {
        const user = event.target.value;
        this.setState({
            user,
        });
    };

    updateDataSourcePass = (event: ChangeEvent<HTMLInputElement>) => {
        const password = event.target.value;
        this.setState({
            password,
        });
    };

    updateClusterId = (value: SelectValue) => {
        const clusterId = parseInt(value as string, 10);
        this.setState({
            clusterId,
        });
    };

    handleSubmit = () => {
        const data = {
            showKey: this.state.showKey,
            configName: '' + this.state.clusterId,
            shardId: this.state.shardId,
            socket: this.state.socket,
            database: this.state.database,
            user: this.state.user,
            password: this.state.password,
            clusterId: this.state.clusterId,
        };
        this.props.addDataSource(data);
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

        const commCfgs = this.props.commCfgs;
        const commCfgList = commCfgs.map((commCfg: ICommCfg) => {
            return (<Option value={'' + commCfg.id} key={commCfg.id}>{commCfg.groupName}</Option>);
        });

        return (
            <Row>
                <Col span={24}>
                    <Form>
                        <FormItem {...formItemLayout} label="集群">
                            <Select value={'' + this.state.clusterId} onChange={this.updateClusterId}>
                                {commCfgList}
                            </Select>
                        </FormItem>
                        <FormItem {...formItemLayout} label="shardId">
                            <Input type="number" value={this.state.shardId}
                                   onChange={this.updateDataSourceshardId}/>
                        </FormItem>
                        <FormItem {...formItemLayout} label="地址">
                            <Input type="text" value={this.state.socket}
                                   onChange={this.updateDataSourceSocket}/>
                        </FormItem>
                        <FormItem {...formItemLayout} label="数据库名称">
                            <Input type="text" value={this.state.database}
                                   onChange={this.updateDataSourceDB}/>
                        </FormItem>
                        <FormItem {...formItemLayout} label="用户名">
                            <Input type="text" value={this.state.user}
                                   onChange={this.updateDataSourceUser}/>
                        </FormItem>
                        <FormItem {...formItemLayout} label="密码">
                            <Input type="text" value={this.state.password}
                                   onChange={this.updateDataSourcePass}/>
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

export default connect(mapStateToProps, mapDispatchToProps)(AddDataSource);