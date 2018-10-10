import * as React from 'react';
import { ChangeEvent } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators, Dispatch } from 'redux';
import { Button, Col, Form, Icon, Input, Row } from 'antd';

import { ILoginData, login } from '../sagas/auth';
import { ICombineState } from '../redux/reducer';
import { IReqAction } from '../sagas/ibaseaction';

import '../custom';
import { History } from 'history';

import { MAIN_PANEL } from '../const/funcType';

const FormItem = Form.Item;

interface IStateProps {
    history: History;
    logined: boolean;
}

interface IDispatchProps {
    login(name: string, pwd: string): IReqAction<ILoginData>;
}

type IAppProps = IStateProps & IDispatchProps;

function mapStateToProps(state: ICombineState) {
    return {
        logined: state.auth.logined,
    };
}

const mapDispatchToProps = (dispatch: Dispatch<{}>) => {
    return bindActionCreators({
        login,
    }, dispatch);
};

interface IState {
    account: string;
    pwd: string;
}

class Login extends React.Component<IAppProps, IState> {

    constructor(props: IAppProps) {
        super(props);

        this.state = {
            account: '',
            pwd: '',
        };
    }

    componentWillReceiveProps(nextProps: IAppProps) {
        if (!this.props.logined && nextProps.logined) {
            console.info('切换到主界面');
            this.props.history.push(MAIN_PANEL);
        }
    }

    handleSubmit = (event: ChangeEvent<HTMLInputElement>) => {
        event.preventDefault();

        this.props.login(this.state.account, this.state.pwd);
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

    render() {
        return (
            <Row style={{height: '100vh'}}>
                <Col offset={8} span={8} className="login">
                    <Form onSubmit={this.handleSubmit} className="login-form">
                        <FormItem>
                            <h2>《征服》管理后台</h2>
                        </FormItem>

                        <FormItem>
                            <Input addonBefore={<Icon type="user"/>} placeholder="用户名" onChange={this.updateAccount}/>
                        </FormItem>

                        <FormItem>
                            <Input addonBefore={<Icon type="lock"/>} type="password" placeholder="密码"
                                   onChange={this.updatePwd}/>
                        </FormItem>

                        <FormItem>
                            <Button type="primary" htmlType="submit" className="login-form-button">
                                登录
                            </Button>
                        </FormItem>
                    </Form>
                </Col>
            </Row>
        );
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Login);