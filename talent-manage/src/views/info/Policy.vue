<template>
    <div>
        <!-- 面包屑导航 -->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>政策指引</el-breadcrumb-item>
        </el-breadcrumb>
        <!-- 搜索筛选 -->
        <el-form :inline="true" :model="formInline" class="user-search">
            <el-form-item label="">
                <el-input size="small" v-model="formInline.keywords" placeholder="输入关键词"></el-input>
            </el-form-item>
            <el-form-item label="">
                <el-input size="small" v-model="formInline.newsId" placeholder="输入政策编号"></el-input>
            </el-form-item>
            <el-form-item label="">
                <div class="block">
                    <!-- <span class="demonstration">默认</span> -->
                    <el-date-picker size="small" v-model="value1" type="daterange" range-separator="至"
                        start-placeholder="开始日期" end-placeholder="结束日期">
                    </el-date-picker>
                </div>
            </el-form-item>

            <el-form-item>
                <el-button size="small" type="primary" icon="el-icon-search" @click="search">搜索</el-button>
                <el-button size="small" type="primary" icon="el-icon-plus" @click="handleEdit()">添加</el-button>
            </el-form-item>

        </el-form>
        <!--列表-->
        <el-table size="small" :data="listData" highlight-current-row v-loading="loading" border
            element-loading-text="拼命加载中" style="width: 100%;">
            <el-table-column align="center" type="selection" width="60">
            </el-table-column>
            <el-table-column prop="title" label="资讯标题" width="300">
            </el-table-column>
            <el-table-column prop="content" label="资讯内容" width="500" show-overflow-tooltip>
            </el-table-column>
            <el-table-column align="center" sortable prop="releaseTime" label="发布时间" width="300">
                <template slot-scope="scope">
                    <div>{{ scope.row.releaseTime }}</div>
                </template>
            </el-table-column>
            <el-table-column prop="img1" label="图片一" width="100">
                <template slot-scope="scope">
                    <el-image :src="scope.row.img1" fit="fill" :lazy="true"></el-image>
                </template>
            </el-table-column>
            <el-table-column prop="img2" label="图片二" width="100">
                <template slot-scope="scope">
                    <el-image :src="scope.row.img1" fit="fill" :lazy="true"></el-image>
                </template>
            </el-table-column>
            <el-table-column prop="img3" label="图片三" width="100">
                <template slot-scope="scope">
                    <el-image :src="scope.row.img1" fit="fill" :lazy="true"></el-image>
                </template>
            </el-table-column>
            <el-table-column align="center" label="操作" min-width="300">
                <template slot-scope="scope">
                    <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button size="mini" type="danger" @click="deleteUser(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <!-- 分页组件 -->
        <Pagination v-bind:child-msg="pageparm" @callFather="callFather"></Pagination>
        <!-- 编辑界面 -->
        <el-dialog :title="title" :visible.sync="editFormVisible" width="30%" @click="closeDialog">
            <el-form label-width="120px" :model="editForm" :rules="rules" ref="editForm">
                <el-form-item label="政策标题" prop="deptName">
                    <el-input size="small" v-model="editForm.deptName" auto-complete="off" placeholder="政策标题"></el-input>
                </el-form-item>
                <el-form-item label="内容" prop="deptNo">
                    <el-input size="small" v-model="editForm.deptNo" auto-complete="off" placeholder="请输入内容"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button size="small" @click="closeDialog">取消</el-button>
                <el-button size="small" type="primary" :loading="loading" class="title"
                    @click="submitForm('editForm')">保存</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import { addPolicy, deletePolicy, getPolicy, getAllPolicy } from '../../api/InfoMG'
import Pagination from '../../components/Pagination'
export default {
    components: {
        Pagination
    },
    data() {
        return {
            nshow: true, //switch开启
            fshow: false, //switch关闭
            loading: false, //是显示加载
            editFormVisible: false, //控制编辑页面显示与隐藏
            title: '添加',
            editForm: {
                deptId: '',
                deptName: '',
                deptNo: '',
                token: localStorage.getItem('logintoken')
            },
            // rules表单验证
            rules: {
                deptName: [
                    { required: true, message: '请输入关键词', trigger: 'blur' }
                ],
                deptNo: [{ required: true, message: '请输入政策编号', trigger: 'blur' }]
            },
            // 筛选信息
            formInline: {
                keywords: "",
                startTime: "",
                endTime: "",
                currentPage: 1,
                pageSize: 5
            },
            value1: [],
            // 删除部门
            seletedata: {
                ids: '',
                token: localStorage.getItem('logintoken')
            },
            //列表数据
            listData: [],
            // 分页参数
            pageparm: {
                currentPage: 1,
                pageSize: 10,
                total: 10
            }
        }
    },

    created() {
        this.getdata(this.formInline);

    },

    methods: {
        // 获取资讯列表
        getdata(param) {
            this.loading = true
            getAllPolicy(param)
                .then(res => {
                    console.log(res);
                    this.loading = false
                    if (res.success == false) {
                        this.$message({
                            type: 'info',
                            message: res.message
                        })
                    } else {
                        this.listData = res.data.data;
                        // 分页赋值
                        this.$set(this.pageparm, "currentPage", this.formInline.currentPage)
                        this.$set(this.pageparm, "pageSize", this.formInline.pageSize)
                        this.pageparm.total = res.data.count
                    }
                })
                .catch(err => {
                    this.loading = false
                    this.$message.error('菜单加载失败，请稍后再试！')
                })
        },



        // 分页插件事件
        callFather(parm) {
            this.$set(this.formInline, "currentPage", parm.currentPage)
            this.$set(this.formInline, "pageSize", parm.pageSize)
            this.getdata(this.formInline)
        },
        // 搜索事件
        search() {
            this.getdata(this.formInline)
        },
        //显示编辑界面
        handleEdit: function (index, row) {
            this.editFormVisible = true
            if (row != undefined && row != 'undefined') {
                this.title = '修改'
                this.editForm.deptId = row.deptId
                this.editForm.deptName = row.deptName
                this.editForm.deptNo = row.deptNo
            } else {
                this.title = '添加'
                this.editForm.deptId = ''
                this.editForm.deptName = ''
                this.editForm.deptNo = ''
            }
        },
        // 编辑、增加页面保存方法
        submitForm(editData) {
            this.$refs[editData].validate(valid => {
                if (valid) {
                    deptSave(this.editForm)
                        .then(res => {
                            this.editFormVisible = false
                            this.loading = false
                            if (res.success) {
                                this.getdata(this.formInline)
                                this.$message({
                                    type: 'success',
                                    message: '公司保存成功！'
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
                            this.$message.error('公司保存失败，请稍后再试！')
                        })
                } else {
                    return false
                }
            })
        },
        // 删除公司
        deleteUser(index, row) {
            this.$confirm('确定要删除吗?', '信息', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            })
                .then(() => {
                    deptDelete(row.deptId)
                        .then(res => {
                            if (res.success) {
                                this.$message({
                                    type: 'success',
                                    message: '公司已删除!'
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
                            this.$message.error('公司删除失败，请稍后再试！')
                        })
                })
                .catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    })
                })
        },
        // 关闭编辑、增加弹出框
        closeDialog() {
            this.editFormVisible = false
        }
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

 
 