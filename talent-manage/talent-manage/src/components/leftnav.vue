/** * 左边菜单 */
<template>
  <el-menu default-active="2" :collapse="collapsed" collapse-transition router :default-active="$route.path" unique-opened
    class="el-menu-vertical-demo" background-color="#334157" text-color="#fff" active-text-color="#ffd04b">
    <div class="logobox">
      <div style="overflow:hidden">人才引进后台</div>
      <img class="logoimg" src="../assets/img/logo.png" alt="" />
    </div>
    <el-submenu v-for="menu in allmenu" :key="menu.menuid" :index="menu.menuname">
      <template slot="title">
        <i class="iconfont" :class="menu.icon"></i>
        <span>{{ menu.menuname }}</span>
      </template>
      <el-menu-item-group>
        <el-menu-item v-for="chmenu in menu.menus" :index="'/' + chmenu.url" :key="chmenu.menuid">
          <i class="iconfont" :class="chmenu.icon"></i>
          <span>{{ chmenu.menuname }}</span>
        </el-menu-item>
      </el-menu-item-group>
    </el-submenu>
  </el-menu>
</template>

<script>
import { menu } from "../api/userMG";
export default {
  name: "leftnav",
  data() {
    return {
      collapsed: false,
      allmenu: []
    };
  },
  created() {
    // 获取图形验证码
    let res = {
      success: true,
      data: [
        {
          menuid: 1,
          icon: "li-icon-xiangmuguanli",
          menuname: "资讯管理",
          hasThird: null,
          url: null,
          menus: [
            {
              menuid: 11,
              icon: "icon-cat-skuQuery",
              menuname: "政策指引",
              hasThird: "N",
              url: "info/Policy",
              menus: null
            },
            {
              menuid: 12,
              icon: "icon-cat-skuQuery",
              menuname: "资讯通知",
              hasThird: "N",
              url: "info/News",
              menus: null
            },
          ]
        },
        {
          menuid: 2,
          icon: "icon-cms-manage",
          menuname: "房源管理",
          hasThird: null,
          url: null,
          menus: [
            {
              menuid: 21,
              icon: "icon-cat-skuQuery",
              menuname: "房源列表",
              hasThird: "N",
              url: "house/House",
              menus: null
            },
            {
              menuid: 22,
              icon: "icon-cat-skuQuery",
              menuname: "房源新增",
              hasThird: "N",
              url: "house/Add",
              menus: null
            },            {
              menuid: 23,
              icon: "icon-cat-skuQuery",
              menuname: "房源搜索",
              hasThird: "N",
              url: "house/Search",
              menus: null
            },
          ]
        },
        {
          menuid: 3,
          icon: "icon-cms-manage",
          menuname: "人才管理",
          hasThird: null,
          url: null,
          menus: [
            {
              menuid: 31,
              icon: "icon-cat-skuQuery",
              menuname: "人才列表",
              hasThird: "N",
              url: "user/User",
              menus: null
            }
          ]
        },
        {
          menuid: 4,
          icon: "li-icon-dingdanguanli",
          menuname: "申请审核",
          hasThird: null,
          url: null,
          menus: [
            {
              menuid: 41,
              icon: "icon-order-manage",
              menuname: "资质审核",
              hasThird: "N",
              url: "qualification/Qualification",
              menus: null
            },
            {
              menuid: 42,
              icon: "icon-order-manage",
              menuname: "入住申请",
              hasThird: "N",
              url: "qualification/Check",
              menus: null
            },
            {
              menuid: 43,
              icon: "icon-order-manage",
              menuname: "补贴申请",
              hasThird: "N",
              url: "qualification/Subsidy",
              menus: null
            },
          ]
        },
        {
          menuid: 5,
          icon: "li-icon-dingdanguanli",
          menuname: "预约管理",
          hasThird: null,
          url: null,
          menus: [
            {
              menuid: 51,
              icon: "icon-order-manage",
              menuname: "预约列表",
              hasThird: "N",
              url: "appoint/Appoint",
              menus: null
            },
          ]
        },
        {
          menuid: 6,
          icon: "li-icon-dingdanguanli",
          menuname: "VR管理",
          hasThird: null,
          url: null,
          menus: [
            {
              menuid: 51,
              icon: "icon-order-manage",
              menuname: "VR列表",
              hasThird: "N",
              url: "vr/Index",
              menus: null
            },
          ]
        },
        {
          menuid: 71,
          icon: "li-icon-xitongguanli",
          menuname: "系统管理",
          hasThird: null,
          url: null,
          menus: [
            {
              menuid: 174,
              icon: "icon-cms-manage",
              menuname: "菜单管理",
              hasThird: "N",
              url: "system/Module",
              menus: null
            },
            {
              menuid: 73,
              icon: "icon-news-manage",
              menuname: "角色管理",
              hasThird: "N",
              url: "system/Role",
              menus: null
            },
            // {
            //   menuid: 74,
            //   icon: "icon-cs-manage",
            //   menuname: "公司管理",
            //   hasThird: "N",
            //   url: "system/Dept",
            //   menus: null
            // },
            {
              menuid: 75,
              icon: "icon-promotion-manage",
              menuname: "系统环境变量",
              hasThird: "N",
              url: "system/Variable",
              menus: null
            },
            {
              menuid: 76,
              icon: "icon-cms-manage",
              menuname: "权限管理",
              hasThird: "N",
              url: "system/Permission",
              menus: null
            }
          ]
        },
        // {
        //   menuid: 128,
        //   icon: "li-icon-shangchengxitongtubiaozitihuayuanwenjian91",
        //   menuname: "支付管理",
        //   hasThird: null,
        //   url: null,
        //   menus: [
        //     {
        //       menuid: 129,
        //       icon: "icon-provider-manage",
        //       menuname: "支付配置信息",
        //       hasThird: "N",
        //       url: "machine/MachineConfig",
        //       menus: null
        //     },
        //     {
        //       menuid: 175,
        //       icon: "icon-provider-manage",
        //       menuname: "支付配置",
        //       hasThird: "N",
        //       url: "pay/Config",
        //       menus: null
        //     }
        //   ]
        // },
        // {
        //   menuid: 150,
        //   icon: "li-icon-shangchengxitongtubiaozitihuayuanwenjian91",
        //   menuname: "图表",
        //   hasThird: null,
        //   url: null,
        //   menus: [
        //     {
        //       menuid: 159,
        //       icon: "icon-provider-manage",
        //       menuname: "数据可视化",
        //       hasThird: "N",
        //       url: "charts/statistics",
        //       menus: null
        //     }
        //   ]
        // }
      ],
      msg: "success"
    };
    this.allmenu = res.data;

    // menu(localStorage.getItem('logintoken'))
    //   .then(res => {
    //     console.log(JSON.stringify(res))
    //     if (res.success) {
    //       this.allmenu = res.data
    //     } else {
    //       this.$message.error(res.msg)
    //       return false
    //     }
    //   })
    //   .catch(err => {
    //     this.$message.error('菜单加载失败，请稍后再试！')
    //   })
    // 监听
    this.$root.Bus.$on("toggle", value => {
      this.collapsed = !value;
    });
  }
};
</script>

<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 240px;
  min-height: 400px;
}

.el-menu-vertical-demo:not(.el-menu--collapse) {
  border: none;
  text-align: left;
}

.el-menu-item-group__title {
  padding: 0px;
}

.el-menu-bg {
  background-color: #1f2d3d !important;
}

.el-menu {
  border: none;
}

.logobox {
  height: 40px;
  line-height: 40px;
  color: #9d9d9d;
  font-size: 20px;
  font-weight: bold;
  padding: 20px 0px;
  display: flex;
  justify-content: space-around;
}

.logoimg {
  height: 40px;
}
</style>
