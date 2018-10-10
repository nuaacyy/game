import { connect } from 'react-redux';

import * as React from 'react';
import { bindActionCreators, Dispatch } from 'redux';
import { Button, Col, Form, Input, Row } from 'antd';
import { modAccPwd } from '../../../sagas/accMgr';
import { ICombineState } from '../../../redux/reducer';
import { ChangeEvent } from 'react';
import { IReqAction } from '../../../sagas/ibaseaction';

const FormItem = Form.Item;

interface IStateProps {
    accName: string;
}

interface IDispatchProps {
    modAccPwd(data: {}): IReqAction<{}>;
}

type IAppProps = IStateProps & IDispatchProps;

function mapStateToProps(state: ICombineState) {
    return {
        accName: state.auth.accName,
    };
}

const mapDispatchToProps = (dispatch: Dispatch<{}>) => {
    return bindActionCreators({
        modAccPwd,
    }, dispatch);
};

interface IState {
    rt: number;
    oldPwd: string;
    newPwd1: string;
    newPwd2: string;
}

class ChgAccPwd extends React.Component<IAppProps, IState> {
    constructor(props: IAppProps) {
        super(props);

        this.state = {
            rt: 0,
            oldPwd: '',
            newPwd1: '',
            newPwd2: '',
        };
    }

    handleSubmit = () => {
        const data = {
            oldPwd: this.state.oldPwd,
            newPwd: this.state.newPwd1,
        };
        this.props.modAccPwd(data);
    };

    updateOldPwd = (event: ChangeEvent<HTMLInputElement>) => {
        const oldPwd = event.target.value;
        this.setState({
            oldPwd,
        });
    };

    updateNewPwd1 = (event: ChangeEvent<HTMLInputElement>) => {
        const newPwd1 = event.target.value;
        this.setState({
            newPwd1,
        });
    };

    updateNewPwd2 = (event: ChangeEvent<HTMLInputElement>) => {
        const newPwd2 = event.target.value;
        this.setState({
            newPwd2,
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
                        <FormItem {...formItemLayout} label="帐号名">
                            <Input type="text" value={this.props.accName} disabled={true}/>
                        </FormItem>
                        <FormItem {...formItemLayout} label="旧密码">
                            <Input type="password" value={this.state.oldPwd}
                                   onChange={this.updateOldPwd}/>
                        </FormItem>
                        <FormItem {...formItemLayout} label="新密码">
                            <Input type="password" value={this.state.newPwd1}
                                   onChange={this.updateNewPwd1}/>
                        </FormItem>
                        <FormItem {...formItemLayout} label="确认密码">
                            <Input type="password" value={this.state.newPwd2}
                                   onChange={this.updateNewPwd2}/>
                        </FormItem>
                        <FormItem {...tailFormItemLayout}>
                            <Button type="primary" onClick={this.handleSubmit}>修改</Button>
                        </FormItem>
                    </Form>
                </Col>
            </Row>
        );
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(ChgAccPwd);