import * as React from 'react';
import { connect } from 'react-redux';

import { bindActionCreators, Dispatch } from 'redux';
import { Button, Col, Form, Input, Radio, Row, Select } from 'antd';
import { addAccount } from '../../../sagas/accMgr';
import { History } from 'history';
import { ICombineState } from '../../../redux/reducer';
import { ACC_MGR } from '../../../const/funcType';
import { IMgrAccGroup } from '../../../redux/modules/accGroup';
import { ChangeEvent } from 'react';
import { IReqAction } from '../../../sagas/ibaseaction';
import { RadioChangeEvent } from 'antd/lib/radio';

const FormItem = Form.Item;
const RadioGroup = Radio.Group;
const Option = Select.Option;

interface IStateProps {
    history: History;
    mgraccgroups: IMgrAccGroup[];
}

interface IDispatchProps {
    addAccount(data: {}): IReqAction<{}>;
}

type IAppProps = IStateProps & IDispatchProps;

function mapStateToProps(state: ICombineState) {
    return {
        mgraccgroups: state.accGroup.mgraccgroups,
    };
}

const mapDispatchToProps = (dispatch: Dispatch<{}>) => {
    return bindActionCreators({
        addAccount,
    }, dispatch);
};

interface IState {
    name: string;
    pwd: string;
    phone: string;
    account: string;
    accstate: number;
    groupId: number;
}

class AddAccount extends React.Component<IAppProps, IState> {

    constructor(props: IAppProps) {
        super(props);

        this.state = {
            pwd: '',
            name: '',
            phone: '',
            account: '',
            accstate: 2,
            groupId: 1,
        };
    }

    cancelAdd = () => {
        this.props.history.push(ACC_MGR);
    };

    handleSubmit = () => {
        const data = {
            pwd: this.state.pwd,
            name: this.state.name,
            phone: this.state.phone,
            groupId: this.state.groupId,
            account: this.state.account,
            accstate: this.state.accstate,
        };

        this.props.addAccount(data);
    };

    updateAccount = (event: ChangeEvent<HTMLInputElement>) => {
        const account = event.target.value;
        this.setState({
            account,
        });
    };

    updatePwd = (event: ChangeEvent<HTMLInputElement>) => {
        const pwd = event.target.value;
        this.setState({
            pwd,
        });
    };

    updateState = (event: RadioChangeEvent) => {
        const accstate = parseInt(event.target.value, 10);
        this.setState({
            accstate,
        });
    };

    updateGroupId = (event: string) => {
        const groupId = parseInt(event, 10);
        this.setState({
            groupId,
        });
    };

    updateName = (event: ChangeEvent<HTMLInputElement>) => {
        const name = event.target.value;
        this.setState({
            name,
        });
    };

    updatePhone = (event: ChangeEvent<HTMLInputElement>) => {
        const phone = event.target.value;
        this.setState({
            phone,
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

        const mgraccgroups = this.props.mgraccgroups;
        const groupList = mgraccgroups.map((mgraccgroup: IMgrAccGroup) => {
            return (<Option value={'' + mgraccgroup.id} key={mgraccgroup.id}>{mgraccgroup.groupName}</Option>);
        });

        return (
            <Row>
                <Col span={24}>

                    <Form>
                        <FormItem {...formItemLayout} label="账号">
                            <Input value={this.state.account} onChange={this.updateAccount}/>
                        </FormItem>

                        <FormItem {...formItemLayout} label="姓名">
                            <Input value={this.state.name} onChange={this.updateName}/>
                        </FormItem>

                        <FormItem {...formItemLayout} label="密码">
                            <Input type="password" value={this.state.pwd} onChange={this.updatePwd}/>
                        </FormItem>

                        <FormItem {...formItemLayout} label="联系方式">
                            <Input type="number" value={this.state.phone} onChange={this.updatePhone}/>
                        </FormItem>

                        <FormItem {...formItemLayout} label="是否启用">
                            <RadioGroup onChange={this.updateState} value={this.state.accstate}>
                                <Radio value={1}>启用</Radio>
                                <Radio value={2}>禁用</Radio>
                            </RadioGroup>
                        </FormItem>

                        <FormItem {...formItemLayout} label="所属账号组">
                            <Select value={'' + this.state.groupId} onChange={this.updateGroupId}>
                                {groupList}
                            </Select>
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

export default connect(mapStateToProps, mapDispatchToProps)(AddAccount);
