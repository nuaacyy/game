import * as React from 'react';
import { bindActionCreators, Dispatch } from 'redux';
import { connect } from 'react-redux';
import { Col, Menu, Row } from 'antd';

import history from '../../appHistory';
import { ICombineState } from '../../redux/reducer';
import {
    ACC_GROUP_MGR,
    ACC_MGR,
    CHG_ACC_PWD,
    COMM_CFG_MGR,
    WORLD_AREA,
    GAME_DEV,
    GAME_MGR,
    GAME_SERVER,
    GM_MGR,
    MAIN_PANEL,
    PLATFORM,
    SERVER_MGR,
    PANEL_TEST,
    DATA_SOURCE,
} from '../../const/funcType';
import { ClickParam } from 'antd/lib/menu';

const SubMenu = Menu.SubMenu;
// const TreeNode = Tree.TreeNode;

// 功能树
interface IStateProps {
    funcType: string;
}

interface IDispatchProps {
}

type IAppProps = IStateProps & IDispatchProps;

function mapStateToProps(state: ICombineState) {
    return {
        funcType: state.main.funcType,
    };
}

function mapDispatchToProps(dispatch: Dispatch<{}>) {
    return bindActionCreators({}, dispatch);
}

interface IFuncMenuNode {
    title: string;
    route: string;
}

interface IFuncMenuItem {
    title: string;
    items: IFuncMenuNode[];
}

const gameDevItems = [
    {
        title: '研发',
        items: [
            {title: '汇总', route: MAIN_PANEL},
        ],
    },
];

const gameMgrItems = [
    {
        title: '主界面',
        items: [
            {title: '汇总', route: MAIN_PANEL},
        ],
    },
];

const gmMgrItems = [
    {
        title: '后台权限管理',
        items: [
            {title: '账号管理', route: ACC_MGR},
            {title: '账号组管理', route: ACC_GROUP_MGR},
            {title: '修改密码', route: CHG_ACC_PWD},
        ],
    },
];

const serverMgrItems = [
    {
        title: '游戏区管理',
        items: [
            {title: '集群配置', route: COMM_CFG_MGR},
            {title: '平台', route: PLATFORM},
            {title: '设备配置', route: GAME_SERVER},
            {title: '游戏服', route: WORLD_AREA},
            {title: '数据库配置', route: DATA_SOURCE},
            {title: '面板测试', route: PANEL_TEST},
        ],
    },
];

const serverMonitorItems = [
    {
        title: '当前状态',
        items: [
            {title: '汇总', route: MAIN_PANEL},
        ],
    },
];

interface IState {

}

class FuncTree extends React.Component<IAppProps, IState> {

    constructor(props: IAppProps) {
        super(props);
    }

    handleClick = (e: ClickParam) => {
        history.push(e.key);
    };

    render() {
        const funcType = this.props.funcType;
        let items = serverMonitorItems;
        if (funcType === GAME_DEV) {
            items = gameDevItems;
        } else if (funcType === GAME_MGR) {
            items = gameMgrItems;
        } else if (funcType === SERVER_MGR) {
            items = serverMgrItems;
        } else if (funcType === GM_MGR) {
            items = gmMgrItems;
        }

        const menuItems = items.map((eachSubMenu: IFuncMenuItem) => {
            const subMenuItem = eachSubMenu.items.map((eachMenuItem: IFuncMenuNode) => {
                return (
                    <Menu.Item key={eachMenuItem.route}>{eachMenuItem.title}</Menu.Item>
                );
            });

            return (
                <SubMenu key={eachSubMenu.title} title={eachSubMenu.title}>
                    {subMenuItem}
                </SubMenu>
            );
        });

        return (
            <Row>
                <Col span={24}>
                    <Menu theme="dark" mode="inline" onClick={this.handleClick}>
                        {menuItems}
                    </Menu>
                </Col>
            </Row>
        );
    }

}

export default connect(mapStateToProps, mapDispatchToProps)(FuncTree);