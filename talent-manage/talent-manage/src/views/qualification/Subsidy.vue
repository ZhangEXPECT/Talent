<template>
    <div>
        <!-- 面包屑导航 -->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>补贴申请</el-breadcrumb-item>
        </el-breadcrumb>
        <!-- 搜索筛选 -->
        <el-form :inline="true" :model="formInline" class="user-search">
            <el-form-item label="搜索：">
                <el-select size="small" v-model="formInline.status" placeholder="请选择">
                    <el-option label="全部" value=" "></el-option>
                    <el-option label="待审核" value="待审核"></el-option>
                    <el-option label="未通过" value="未通过"></el-option>
                    <el-option label="通过" value="通过"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="">
                <el-input size="small" v-model="formInline.userName" placeholder="输入用户名"></el-input>
            </el-form-item>
            <el-form-item label="">
                <el-input size="small" v-model="formInline.userPhone" placeholder="输入手机号"></el-input>
            </el-form-item>
            <el-form-item label="">
                <el-input size="small" v-model="formInline.userPhone" placeholder="输入身份证"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button size="small" type="primary" icon="el-icon-search" @click="search">搜索</el-button>
                <!-- <el-button size="small" type="primary" icon="el-icon-plus" @click="handleEdit()">添加</el-button> -->
            </el-form-item>
        </el-form>
        <!--列表-->
        <el-table size="small" @selection-change="selectChange" :data="subsidyData" highlight-current-row
            v-loading="loading" border element-loading-text="拼命加载中" style="width: 100%;">
            <el-table-column align="center" type="selection" width="50">
            </el-table-column>
            <el-table-column align="center" prop="user.realName" label="姓名" width="120">
            </el-table-column>
            <el-table-column align="center" prop="user.gender" label="性别" min-width="50">
            </el-table-column>
            <el-table-column align="center" sortable prop="user.phone" label="手机号" width="120">
            </el-table-column>
            <el-table-column align="center" sortable prop="user.idcard" label="身份证号" min-width="150">
            </el-table-column>
            <el-table-column align="center" prop="merried" label="婚姻状态" min-width="80">
                <template slot-scope="scope">
                    <div v-if="scope.row.merried == 1">已婚</div>
                    <div v-else>未婚</div>
                </template>
            </el-table-column>
            <el-table-column align="center" sortable prop="local" label="是否本地" min-width="80">
                <template slot-scope="scope">
                    <div v-if="scope.row.merried == 1">是</div>
                    <div v-else>否</div>
                </template>
            </el-table-column>
            <el-table-column align="center" prop="employed" label="是否就业" min-width="80">
                <template slot-scope="scope">
                    <div v-if="scope.row.employed == 1">已就业</div>
                    <div v-else>未就业</div>
                </template>
            </el-table-column>
            <el-table-column align="center" prop="school" label="学校" min-width="120">
            </el-table-column>
            <el-table-column align="center" sortable prop="degree" label="学历" min-width="80">
            </el-table-column>
            <el-table-column align="center"  prop="major" label="专业" min-width="80">
            </el-table-column>
            <el-table-column align="center"  prop="liveAddress" label="居住地址" min-width="80">
            </el-table-column>
            <el-table-column align="center"  prop="landlordName" label="房东姓名" min-width="80">
            </el-table-column>
            <el-table-column align="center"  prop="landlordPhone" label="房东电话" min-width="80">
            </el-table-column>
            <el-table-column align="center" sortable prop="liveStart" label="入住时间" min-width="150">
            </el-table-column>
            <el-table-column align="center" sortable prop="liveEnd" label="到期时间" min-width="150">
            </el-table-column>
            <el-table-column align="center" sortable prop="buildTime" label="申请时间" min-width="150">
                <template slot-scope="scope">
                    <div>{{ scope.row.buildTime }}</div>
                </template>
            </el-table-column>
            <el-table-column align="center" sortable prop="status" label="状态" min-width="80">
            </el-table-column>
            <el-table-column align="center" label="操作" min-width="350" fixed="right">
                <template slot-scope="scope">
                    <el-button size="mini" type="danger" @click="deleteUser(scope.$index, scope.row)">删除</el-button>
                    <el-button size="mini" type="success" @click="handleEdit(scope.$index, scope.row)">审核</el-button>
                </template>
            </el-table-column>
        </el-table>
        <!-- 分页组件 -->
        <Pagination v-bind:child-msg="pageparm" @callFather="callFather"></Pagination>
        <!-- 编辑界面 -->
        <el-dialog :title="title" :visible.sync="editFormVisible" width="40%" @click='closeDialog("edit")'>
            <el-form label-width="80px" ref="editForm" :model="editForm" :rules="rules">
                <!-- <el-form-item label="角色" prop="roleId">
                    <el-select size="small" v-model="editForm.roleId" placeholder="请选择" class="userRole">
                        <el-option label="公司管理员" value="1"></el-option>
                        <el-option label="普通用户" value="2"></el-option>
                    </el-select>
                </el-form-item> -->
                <el-form-item label="状态：" prop="status">
                    <el-radio v-model="editForm.status" label="待审核">待审核</el-radio>
                    <el-radio v-model="editForm.status" label="通过">通过</el-radio>
                    <el-radio v-model="editForm.status" label="未通过">未通过</el-radio>
                </el-form-item>
                <el-form-item label="原因：" prop="remark" v-if="editForm.status == '未通过'">
                    <el-input size="small" v-model="editForm.remark" auto-complete="off" type="textarea" autosize
                        placeholder="请输入未通过原因"></el-input>
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
    getAllSubsidy,updateSubsidy
} from '../../api/qualificationMG'
import Pagination from '../../components/Pagination'
export default {
    // 注册组件
    components: {
        Pagination
    },
    data() {
        return {
            title:'',
            loading: false, //是显示加载
            editFormVisible: false, //控制编辑页面显示与隐藏
            // 编辑与添加
            editForm: {
                status:'',
                remark:'',
                subsidyId:0
            },
            // 选择数据
            selectdata: [],
            // rules表单验证
            rules: {
                remark: [{ required: true, message: '原因不能为空', trigger: 'blur' }]
            },
            // 筛选信息
            formInline: {
                status: "",
                currentPage: 1,
                pageSize: 10
            },
            //数据
            subsidyData: [],
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
    watch: {},

    created() {
        this.getdata(this.formInline)
    },

    methods: {
        // 获取用户数据
        getdata(param) {
            this.loading = true
            // 获取审核列表
            getAllSubsidy(param).then(res => {
                console.log(res)
                console.log(res.success);
                this.loading = false
                if (res.status !== 200) {
                    this.$message({
                        type: 'info',
                        message: res.data.message
                    })
                } else {
                    this.subsidyData = res.data.data.data
                    // 分页赋值
                    this.pageparm.currentPage = this.formInline.currentPage
                    this.pageparm.pageSize = this.formInline.pageSize
                    this.pageparm.total = res.data.data.count
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
                this.title = '审核'
                this.editForm.subsidyId = row.subsidyId
                this.editForm.status = row.status
                this.editForm.remark = row.rermark
            } else {
                // this.title = '添加用户'
                // this.editForm.userId = ''
                // this.editForm.userName = ''
                // this.editForm.userRealName = ''
                // this.editForm.roleId = ''
                // this.editForm.userMobile = ''
                // this.editForm.userEmail = ''
                // this.editForm.userSex = ''
            }
        },
        // 编辑、添加提交方法
        submitForm(editData) {
            this.$refs[editData].validate(valid => {
                if (valid) {
                    // 请求方法
                    updateSubsidy(this.editForm)
                        .then(res => {
                            this.editFormVisible = false
                            this.loading = false
                            if (res.status ==200) {
                                this.getdata(this.formInline)
                                this.$message({
                                    type: 'success',
                                    message: '数据保存成功！'
                                })
                            } else {
                                this.$message({
                                    type: 'info',
                                    message: res.message
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
        handleClick(data, checked, node) {
            if (checked) {
                this.$refs.tree.setCheckedNodes([])
                this.$refs.tree.setCheckedNodes([data])
                this.unitparm.deptId = data.id
                this.unitparm.deptName = data.name
                //交叉点击节点
            } else {
            }
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
        deleteUser(index, row) {
            this.$confirm('确定要删除吗?', '信息', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            })
                .then(() => {
                    // 删除
                    userDelete(row.id)
                        .then(res => {
                            if (res.success) {
                                this.$message({
                                    type: 'success',
                                    message: '数据已删除!'
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

 