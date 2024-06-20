<template>
    <div>
        <!-- 面包屑导航 -->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>预约管理</el-breadcrumb-item>
        </el-breadcrumb>
        <!-- 搜索筛选 -->
        <el-form :inline="true" :model="formInline" class="user-search">
            <el-form-item label="搜索：">
                <el-select size="small" v-model="formInline.status" placeholder="请选择">
                    <el-option label="全部" value=""></el-option>
                    <el-option label="待处理" value="待处理"></el-option>
                    <el-option label="已结束" value="已结束"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="">
                <el-input size="small" v-model="formInline.userName" placeholder="输入用户名"></el-input>
            </el-form-item>
            <el-form-item label="">
                <el-input size="small" v-model="formInline.userPhone" placeholder="输入手机号"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button size="small" type="primary" icon="el-icon-search" @click="search">搜索</el-button>
            </el-form-item>
        </el-form>
        <!--列表-->
        <el-table size="small" @selection-change="selectChange" :data="appointData" highlight-current-row
            v-loading="loading" border element-loading-text="拼命加载中" style="width: 100%;">
            <el-table-column align="center" type="selection" width="50">
            </el-table-column>
            <el-table-column align="center" sortable prop="appointId" label="预约编号" width="100">
            </el-table-column>
            <el-table-column align="center" sortable prop="user.realName" label="姓名" width="120">
            </el-table-column>
            <el-table-column align="center" prop="user.phone" label="电话" width="120">
            </el-table-column>
            <el-table-column align="center" prop="house.address" label="预约地址" width="120">
            </el-table-column>
            <el-table-column align="center" prop="house.cellName" label="公寓名称" min-width="170">
            </el-table-column>
            <el-table-column align="center" sortable prop="house.layoutType" label="类型" min-width="150">
            </el-table-column>
            <el-table-column align="center" sortable prop="buildTime" label="申请时间" min-width="150">
            </el-table-column>
            <el-table-column align="center" sortable prop="appointTime" label="预约时间" min-width="150">
            </el-table-column>
            <el-table-column align="center" sortable prop="status" label="状态" min-width="120">
            </el-table-column>
            <el-table-column align="center" label="操作" min-width="200" fixed="right">
                <template slot-scope="scope">
                    <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                    <el-button size="mini" type="success" @click="handleEdit(scope.$index, scope.row)">修改状态</el-button>
                </template>
            </el-table-column>
        </el-table>
        <!-- 分页组件 -->
        <Pagination v-bind:child-msg="pageparm" @callFather="callFather"></Pagination>
        <!-- 编辑界面 -->
        <el-dialog :title="title" :visible.sync="editFormVisible" width="30%" @click='closeDialog("edit")'>
            <el-form label-width="80px" ref="editForm" :model="editForm" :rules="rules">
                <el-form-item label="预约状态" prop="status">
                    <el-input size="small" v-model="editForm.status" auto-complete="off" placeholder="预约完成填入已结束"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button size="small" @click='closeDialog("edit")'>取消</el-button>
                <el-button size="small" type="primary" :loading="loading" class="title"
                    @click="submitForm('editForm')">保存</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
// 导入请求方法
import {
    addAppoint, updateAppoint, deleteAppoint, getAppoint, getAllAppoint
} from '../../api/qualificationMG'
import Pagination from '../../components/Pagination'
export default {
    // 注册组件
    components: {
        Pagination
    },
    data() {
        return {
            loading: false, //是显示加载
            title: '添加用户',
            editFormVisible: false, //控制编辑页面显示与隐藏
            // 编辑与添加
            editForm: {
                appointId:0,
                status: '',
                // userId:'',
                // houseId:'',
                // buildTime:'',
                // appointTime:'',
            },
            // 选择数据
            selectdata: [],
            rules: {
                status: [{ required: true, message: '请选择待处理、已结束填入', trigger: 'blur' }],
            },
            // 删除用户
            seletedata: {
                ids: '',
            },

            formInline: {
                status: "",
                currentPage: 1,
                pageSize: 10
            },
            //预约数据
            appointData: [],
            defaultProps: {
                children: 'children',
                label: 'name'
            },
            // 选中
            checkmenu: [],
            //参数role
            saveroleId: '',
            // 分页参数
            pageparm: {
                currentPage: 1,
                pageSize: 10,
                total: 10
            }
        }
    },

    created() {
        this.getdata(this.formInline)
    },

    methods: {
        // 获取数据
        getdata(param) {
            this.loading = true
            // 获取列表
            getAllAppoint(param).then(res => {
                console.log(res)
                this.loading = false
                if (res.status !== 200) {
                    this.$message({
                        type: 'info',
                        message: res.message
                    })
                } else {
                    this.appointData = res.data.data.data
                    // 分页赋值
                    this.pageparm.currentPage = this.formInline.currentPage
                    this.pageparm.pageSize = this.formInline.pageSize
                    this.pageparm.total = res.data.data.data.length
                }
            })
        },
        // 分页插件事件
        callFather(parm) {
            this.formInline.currentPage = parm.currentPage
            this.formInline.pageSize = parm.pageSize
            this.getdata(this.formInline)
        },
        //搜索事件
        search() {
            this.getdata(this.formInline)
        },

        //显示编辑界面
        handleEdit: function (index, row) {
            this.editFormVisible = true
            if (row != undefined && row != 'undefined') {
                this.title = '修改状态'
                console.log(row);
                this.editForm.status = row.status
                this.editForm.appointId = row.appointId
            } else {
                this.title = '添加用户'
                this.editForm.userId = ''
                this.editForm.userName = ''
                this.editForm.userRealName = ''
                this.editForm.roleId = ''
                this.editForm.userMobile = ''
                this.editForm.userEmail = ''
                this.editForm.userSex = ''
            }
        },
        // 编辑、添加提交方法
        submitForm(editData) {
            let that = this;
            this.$refs[editData].validate(valid => {
                if (valid) {
                    updateAppoint(this.editForm)
                        .then(res => {
                            this.editFormVisible = false
                            this.loading = false
                            if (res.status==200) {
                                this.getdata(this.formInline)
                                this.$message({
                                    type: 'success',
                                    message: '数据保存成功！'
                                })
                            } else {
                                this.$message({
                                    type: 'info',
                                    message: res.msg
                                })
                            }
                        })
                        .catch(err => {
                            this.editFormVisible = false
                            this.loading = false
                            this.$message.error('保存失败，请稍后再试！')
                        })
                } else {
                    return false
                }
            })
        },
        // 选择复选框事件
        selectChange(val) {
            this.selectdata = val
        },
        // 关闭编辑、增加弹出框
        closeDialog(dialog) {
            if (dialog == 'edit') {
                this.editFormVisible = false
            } else if (dialog == 'perm') {
                this.dataAccessshow = false
            } else if (dialog == 'unit') {
                this.unitAccessshow = false
            }
        },
        // 删除用户
        handleDelete(index, row) {
            this.$confirm('确定要删除吗?', '信息', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                    // 删除
                    deleteAppoint(row.appointId)
                        .then(res => {
                            if (res.code==200) {
                                this.$message({
                                    type: 'success',
                                    message: '数据已删除!'
                                })
                                this.getdata(this.formInline)
                            } else {
                                this.$message({
                                    type: 'info',
                                    message: res.message
                                })
                            }
                        })
                        .catch(err => {
                            this.loading = false
                            this.$message.error('数据删除失败，请稍后再试！')
                        })
                })
                .catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除！'
                    })
                })
        },
        // 重置密码
        resetpwd(index, row) {
            this.resetpsd.userId = row.userId
            this.$confirm('确定要重置密码吗?', '信息', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            })
                .then(() => {
                    userPwd(this.resetpsd)
                        .then(res => {
                            if (res.success) {
                                this.$message({
                                    type: 'success',
                                    message: '重置密码成功！'
                                })
                                this.getdata(this.formInline)
                            } else {
                                this.$message({
                                    type: 'info',
                                    message: res.msg
                                })
                            }
                        })
                        .catch(err => {
                            this.loading = false
                            this.$message.error('重置密码失败，请稍后再试！')
                        })
                })
                .catch(() => {
                    this.$message({
                        type: 'info',
                        message: '取消重置密码！'
                    })
                })
        },

        //数据格式化
        changeArr(data) {
            var pos = {}
            var tree = []
            var i = 0
            while (data.length != 0) {
                if (data[i].pId == 0) {
                    tree.push({
                        id: data[i].id,
                        name: data[i].name,
                        pId: data[i].pId,
                        open: data[i].open,
                        checked: data[i].checked,
                        children: []
                    })
                    pos[data[i].id] = [tree.length - 1]
                    data.splice(i, 1)
                    i--
                } else {
                    var posArr = pos[data[i].pId]
                    if (posArr != undefined) {
                        var obj = tree[posArr[0]]
                        for (var j = 1; j < posArr.length; j++) {
                            obj = obj.children[posArr[j]]
                        }

                        obj.children.push({
                            id: data[i].id,
                            name: data[i].name,
                            pId: data[i].pId,
                            open: data[i].open,
                            checked: data[i].checked,
                            children: []
                        })
                        pos[data[i].id] = posArr.concat([obj.children.length - 1])
                        data.splice(i, 1)
                        i--
                    }
                }
                i++
                if (i > data.length - 1) {
                    i = 0
                }
            }
            return tree
        },
        // 下线用户
        offlineUser(index, row) {
            this.$confirm('确定要让' + row.userName + '用户下线吗?', '信息', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            })
                .then(() => {
                    userExpireToken(row.userName)
                        .then(res => {
                            if (res.success) {
                                this.$message({
                                    type: 'success',
                                    message: '用户' + row.userName + '强制下线成功！'
                                })
                                this.getdata(this.formInline)
                            } else {
                                this.$message({
                                    type: 'info',
                                    message: res.msg
                                })
                            }
                        })
                        .catch(err => {
                            this.loading = false
                            this.$message.error('用户下线失败，请稍后再试！')
                        })
                })
                .catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消'
                    })
                })
        },

    }
}
</script>

<style scoped>
.user-search {
    margin-top: 20px;
}

.userRole {
    width: 100%;
}
</style>

 