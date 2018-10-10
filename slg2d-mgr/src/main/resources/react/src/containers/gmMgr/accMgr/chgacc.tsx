import * as React from 'react';
import { ChangeEvent } from 'react';
import { connect } from 'react-redux';
import { match } from 'react-router';
import { bindActionCreators, Dispatch } from 'redux';
import { Button, Col, Form, Input, Radio, Row, Select } from 'antd';
import { chgAccount } from '../../../sagas/accMgr';
import { History } from 'history';
import { ICombineState } from '../../../redux/reducer';
import { ACC_MGR } from '../../../const/funcType';
import { IMgrAccount } from '../../../redux/modules/acc';
import { IMgrAccGroup } from '../../../redux/modules/accGroup';
import { IReqAction } from '../../../sagas/ibaseaction';
import { RadioChangeEvent } from 'antd/lib/radio';

const Option = Select.Option;
const FormItem = Form.Item;
const RadioGroup = Radio.Group;

interface IChgAccParam {
    accId: string;
}

interface IStateProps {
    history: History;
    match: match<IChgAccParam>;
    mgraccounts: IMgrAccount[];
    mgraccgroups: IMgrAccGroup[];
}

interface IDispatchProps {
    chgAccount(data: {}): IReqAction<{}>;
}

type IAppProps = IStateProps & IDispatchProps;

function mapStateToProps(state: ICombineState) {
    return {
        mgraccounts: state.acc.mgraccounts,
        mgraccgroups: state.accGroup.mgraccgroups,
    };
}

const mapDispatchToProps = (dispatch: Dispatch<{}>) => {
    return bindActionCreators({
        chgAccount,
    }, dispatch);
};

interface IState {
    id: number;
    name: string;
    phone: string;
    account: string;
    groupId: number;
    accstate: number;
}

class ChgAccount extends React.Component<IAppProps, IState> {

    constructor(props: IAppProps) {
        super(props);

        const targetAccountId = parseInt(this.props.match.params.accId, 10);
        let targetAccount = null;
        for (const mgrAccount of this.props.mgraccounts) {
            if (mgrAccount.id === targetAccountId) {
                targetAccount = mgrAccount;
                break;
            }
        }

        if (!(targetAccount == null || targetAccount === undefined)) {
            this.state = {
                id: targetAccountId,
                name: targetAccount.name,
                phone: targetAccount.phone,
                account: targetAccount.accName,
                groupId: targetAccount.groupId,
                accstate: targetAccount.accstate,
            };
        } else {
            this.state = {
                id: targetAccountId,
                name: '',
                phone: '',
                account: '',
                groupId: 0,
                accstate: 0,
            };
        }
    }

    cancelAdd = () => {
        this.props.history.push(ACC_MGR);
    };

    handleSubmit = () => {

        const data = {
            name: this.state.name,
            phone: this.state.phone,
            accountId: this.state.id,
            account: this.state.account,
            groupId: this.state.groupId,
            accstate: this.state.accstate,
        };

        this.props.chgAccount(data);
        this.props.history.push(ACC_MGR);
    };

    updateAccount = (event: ChangeEvent<HTMLInputElement>) => {
        const account = event.target.value;
        this.setState({
            account,
        });
    };

    updateState = (event: RadioChangeEvent) => {
        const accstate = parseInt(event.target.value, 10);
        this.setState({
            accstate,
        });
    };

    updateGroupId = (event: string) => {
        const v = parseInt(event, 10);
        this.setState({
            groupId: v,
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
                            <Button type="primary" onClick={this.handleSubmit}>修改</Button>
                            <Button type="primary" onClick={this.cancelAdd}>取消</Button>
                        </FormItem>
                    </Form>
                </Col>
            </Row>
        );
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(ChgAccount);