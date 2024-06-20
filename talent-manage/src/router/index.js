// 导入组件
import Vue from 'vue';
import Router from 'vue-router';
// 登录
import login from '@/views/login';
// 首页
import index from '@/views/index';
/**
 * 基础菜单
 */
// 资讯信息
import News from '@/views/info/News';
// 政策信息
import Policy from '@/views/info/Policy';
import House from '@/views/house/House';
import Add from '@/views/house/Add';
import Search from '@/views/house/Search';
import User from '@/views/user/User';
import Qualification from '@/views/qualification/Qualification';
import Check from '@/views/qualification/Check';
import Subsidy from '@/views/qualification/Subsidy';
import Appoint from '@/views/appoint/Appoint';
import Vr from '@/views/vr/Index';

// 商品管理
import Goods from '@/views/goods/Goods';
// 机器信息管理
import Machine from '@/views/machine/Machine';
// 货道信息管理
import MachineAisle from '@/views/machine/MachineAisle';
/**
 * 订单管理
 */
// 交易订单
import Order from '@/views/pay/Order';
/**
 * 系统管理
 */
// 用户管理
import user from '@/views/system/user';
// 菜单管理
import Module from '@/views/system/Module';
// 角色管理
import Role from '@/views/system/Role';
// 公司管理
import Dept from '@/views/system/Dept';
// 系统环境变量
import Variable from '@/views/system/Variable';
// 权限管理
import Permission from '@/views/system/Permission';
/**
 * 支付管理
 */
// 支付配置信息
import MachineConfig from '@/views/machine/MachineConfig';
// 支付配置
import Config from '@/views/pay/Config';
/**
 * 数据监控
 */
// 监控查询
import druidLogin from '@/views/druid/login';

// 图表界面
import statistics from '@/views/charts/statistics';

// 启用路由
Vue.use(Router);

// 导出路由 
export default new Router({
    routes: [{
        path: '/',
        name: '',
        component: login,
        hidden: true,
        meta: {
            requireAuth: false
        }
    }, {
        path: '/login',
        name: '登录',
        component: login,
        hidden: true,
        meta: {
            requireAuth: false
        }
    }, {
        path: '/index',
        name: '首页',
        component: index,
        iconCls: 'el-icon-tickets',
        children: [{
            path: '/info/Policy',
            name: '政策信息',
            component: Policy,
            meta: {
                requireAuth: true
            }
        }, {
            path: '/info/News',
            name: '资讯公示',
            component: News,
            meta: {
                requireAuth: true
            }

        }, {
            path: '/house/House',
            name: '房源管理',
            component: House,
            meta: {
                requireAuth: true
            }
        },
        {
            path: '/house/Add',
            name: '房源添加',
            component: Add,
            meta: {
                requireAuth: true
            }
        },
        {
            path: '/house/Search',
            name: '房源搜索',
            component: Search,
            meta: {
                requireAuth: true
            }
        },
        {
            path: '/user/User',
            name: '人才管理',
            component: User,
            meta: {
                requireAuth: true
            }
        },
        {
            path: '/qualification/Qualification',
            name: '资格审核',
            component: Qualification,
            meta: {
                requireAuth: true
            }
        },
        {
            path: '/qualification/Check',
            name: '入住申请',
            component: Check,
            meta: {
                requireAuth: true
            }
        },
        {
            path: '/qualification/Subsidy',
            name: '补贴申请',
            component: Subsidy,
            meta: {
                requireAuth: true
            }
        },
        {
            path: '/appoint/Appoint',
            name: '预约管理',
            component: Appoint,
            meta: {
                requireAuth: true
            }
        },
        {
            path: '/vr/Index',
            name: 'VR管理',
            component: Vr,
            meta: {
                requireAuth: true
            }
        },

        {
            path: '/machine/Machine',
            name: '机器信息管理',
            component: Machine,
            meta: {
                requireAuth: true
            }
        }, {
            path: '/machine/MachineAisle',
            name: '货道信息管理',
            component: MachineAisle,
            meta: {
                requireAuth: true
            }
        }, {
            path: '/pay/Order',
            name: '交易订单',
            component: Order,
            meta: {
                requireAuth: true
            }
        }, {
            path: '/system/user',
            name: '用户管理',
            component: user,
            meta: {
                requireAuth: true
            }
        }, {
            path: '/system/Module',
            name: '菜单管理',
            component: Module,
            meta: {
                requireAuth: true
            }
        }, {
            path: '/system/Role',
            name: '角色管理',
            component: Role,
            meta: {
                requireAuth: true
            }
        }, {
            path: '/system/Dept',
            name: '公司管理',
            component: Dept,
            meta: {
                requireAuth: true
            }
        }, {
            path: '/system/Variable',
            name: '系统环境变量',
            component: Variable,
            meta: {
                requireAuth: true
            }
        }, {
            path: '/system/Permission',
            name: '权限管理',
            component: Permission,
            meta: {
                requireAuth: true
            }
        }, {
            path: '/machine/MachineConfig',
            name: '支付配置信息',
            component: MachineConfig,
            meta: {
                requireAuth: true
            }
        }, {
            path: '/pay/Config',
            name: '支付配置',
            component: Config,
            meta: {
                requireAuth: true
            }
        }, {
            path: '/druid/login',
            name: '监控查询',
            component: druidLogin,
            meta: {
                requireAuth: true
            }
        }, ]
    }]
})