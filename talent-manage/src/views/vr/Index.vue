<template>
    <div>
        <!-- 面包屑导航 -->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>VR列表</el-breadcrumb-item>
        </el-breadcrumb>
        <!-- 搜索筛选 -->
        <el-form :inline="true" :model="formInline" class="user-search">
            <el-form-item label="">
                <el-input size="small" v-model="formInline.vrId" placeholder="输入VR编号"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button size="small" type="primary" icon="el-icon-search" @click="search">搜索</el-button>
                <el-button size="small" type="primary" icon="el-icon-plus" @click="handleAdd()">添加</el-button>
            </el-form-item>

        </el-form>
        <!--列表-->
        <el-table size="small" :data="listData" highlight-current-row v-loading="loading" border
            element-loading-text="拼命加载中" style="width: 100%;">
            <el-table-column align="center" type="selection" width="60">
            </el-table-column>
            <el-table-column prop="vrId" label="vrId" width="50">
            </el-table-column>
            <el-table-column prop="houseId" label="houseId" width="80">
            </el-table-column>
            <el-table-column prop="vrUrl" label="vrUrl" width="300">
            </el-table-column>
            <el-table-column prop="vrContent" label="vrContent" width="300">
            </el-table-column>
            <el-table-column prop="img1" label="图片一" width="100">
                <template slot-scope="scope">
                    <el-image :src="scope.row.img1" fit="fill" :lazy="true"></el-image>
                </template>
            </el-table-column>
            <el-table-column prop="img2" label="图片二" width="100">
                <template slot-scope="scope">
                    <el-image :src="scope.row.img2" fit="fill" :lazy="true"></el-image>
                </template>
            </el-table-column>
            <el-table-column prop="img3" label="图片三" width="100">
                <template slot-scope="scope">
                    <el-image :src="scope.row.img3" fit="fill" :lazy="true"></el-image>
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
        <el-pagination @size-change="size_change" @current-change="current_change" :current-page="currentPage"
            :page-sizes="[9, 20, 30]" :page-size="pagesize" layout="total, sizes, prev, pager, next, jumper" :total="listData.length
                ">
        </el-pagination>
        <!-- 编辑界面 -->
        <el-dialog :title="title" :visible.sync="editFormVisible" width="30%" @click="closeDialog">
            <el-form label-width="120px" :model="editForm" :rules="rules" ref="editForm">
                <el-form-item label="VR编号" prop="vrId">
                    <el-input size="small" v-model="editForm.vrId" auto-complete="off" placeholder="请输入VR链接"
                        disabled></el-input>
                </el-form-item>
                <el-form-item label="房源编号" prop="houseId">
                    <el-input size="small" v-model="editForm.houseId" auto-complete="off" placeholder="请输入房源编号"></el-input>
                </el-form-item>
                <el-form-item label="VR链接" prop="vrUrl">
                    <el-input size="small" v-model="editForm.vrUrl" auto-complete="off" placeholder="请输入VR链接"></el-input>
                </el-form-item>
                <el-form-item label="VR简介" prop="vrContent">
                    <el-input size="small" v-model="editForm.vrContent" auto-complete="off"
                        placeholder="请输入VR简介"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button size="small" @click="closeDialog">取消</el-button>
                <el-button size="small" type="primary" :loading="loading" class="title"
                    @click="submitForm('editForm')">保存</el-button>
            </div>
        </el-dialog>

        <el-dialog :title="title" :visible.sync="addFormVisible" width="30%" @click="closeDialog">
            <el-form label-width="120px" :model="editForm" :rules="rules" ref="editForm">
                <el-form-item label="VR编号" prop="vrId">
                    <el-input size="small" v-model="editForm.vrId" auto-complete="off" placeholder="请输入VR链接"></el-input>
                </el-form-item>
                <el-form-item label="房源编号" prop="houseId">
                    <el-input size="small" v-model="editForm.houseId" auto-complete="off" placeholder="请输入房源编号"></el-input>
                </el-form-item>
                <el-form-item label="VR链接" prop="vrUrl">
                    <el-input size="small" v-model="editForm.vrUrl" auto-complete="off" placeholder="请输入VR链接"></el-input>
                </el-form-item>
                <el-form-item label="VR简介" prop="vrContent">
                    <el-input size="small" v-model="editForm.vrContent" auto-complete="off"
                        placeholder="请输入VR简介"></el-input>
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

import { addVR, deleteVR, getVR, getAllVR, updateVR } from '../../api/InfoMG'
import Pagination from '../../components/Pagination'
export default {
    components: {
        Pagination
    },
    data() {
        return {
            loading: false, //是显示加载
            editFormVisible: false, //控制编辑页面显示与隐藏
            addFormVisible: false,
            title: '添加',
            editForm: {
                vrId: "",
                houseId: '',
                vrUrl: '',
                vrContent: '',
                img1: "",
                img2: "",
                img3: "",
                img4: "",
                img5: "",

            },
            // rules表单验证
            rules: {
                houseId: [
                    { required: true, message: '请输入房源编号', trigger: 'blur' }
                ],
                vrUrl: [{ required: true, message: '请输入VR链接', trigger: 'blur' }],
            },
            // 筛选信息
            formInline: {
                vrId: "",
                currentPage: 1,
                pageSize: 5
            },
            value1: [],

            //列表数据
            listData: [],
            // 分页参数
            total: 0,//总条目数
            pagesize: 9,//每页显示条目个数
            currentPage: 1,//当前页数
        }
    },

    created() {
        this.getdata(this.formInline);

    },

    methods: {
        // 获取资讯列表
        getdata(param) {
            this.loading = true
            getAllVR(param)
                .then(res => {
                    this.loading = false
                    if (res.code !== 200) {
                        this.$message({
                            type: 'info',
                            message: res.message
                        })
                    } else {
                        this.listData = res.data.data;
                    }
                })
                .catch(err => {
                    this.loading = false
                    this.$message.error('数据加载失败，请稍后再试！')
                })
        },


        //监听 pagesize 改变的事件
        size_change(newSize) {
            this.pagesize = newSize
        },
        //监听 页码值 改变的事件
        current_change(newPage) {
            this.currentPage = newPage
        },

        // 搜索事件
        search() {
            this.getdata(this.formInline)
        },
        //显示编辑界面
        handleEdit: function (index, row) {
            this.editFormVisible = true
            this.title = '修改'
            this.editForm.vrId = row.vrId
            this.editForm.houseId = row.houseId
            this.editForm.vrUrl = row.vrUrl
            this.editForm.vrContent = row.vrContent
            this.editForm.img1 = row.img1
            this.editForm.img2 = row.img2
            this.editForm.img3 = row.img3
            this.editForm.img4 = row.img4
            this.editForm.img5 = row.img5
        },
        // 编辑、增加页面保存方法
        submitForm(editData) {
            let that = this;
            this.$refs[editData].validate(valid => {
                if (valid) {
                    updateVR(this.editForm)
                        .then(res => {
                            this.editFormVisible = false
                            this.loading = false
                            if (res.code == 200) {
                                that.getdata(this.formInline)
                                this.$message({
                                    type: 'success',
                                    message: '保存成功！'
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

        //显示编辑界面
        handleAdd: function () {
            this.addFormVisible = true
            this.title = '添加'
            this.editForm.vrId = ""
            this.editForm.houseId = ""
            this.editForm.vrUrl = ""
            this.editForm.vrContent = ""
            this.editForm.img1 = "https://img.zcool.cn/community/0104925de1e490a80120686b47b6c3.jpg@1280w_1l_2o_100sh.jpg"
            this.editForm.img2 = "https://img.zcool.cn/community/0104925de1e490a80120686b47b6c3.jpg@1280w_1l_2o_100sh.jpg"
            this.editForm.img3 = ""
            this.editForm.img4 = ""
            this.editForm.img5 = ""
        },
        // 编辑、增加页面保存方法
        submitForm(editData) {
            let that = this;
            this.$refs[editData].validate(valid => {
                if (valid) {
                    addVR(this.editForm)
                        .then(res => {
                            this.addFormVisible = false
                            this.loading = false
                            if (res.code == 202) {
                                that.getdata(this.formInline)
                                this.$message({
                                    type: 'success',
                                    message: '添加成功！'
                                })
                            } else {
                                this.$message({
                                    type: 'info',
                                    message: res.message
                                })
                            }
                        })
                        .catch(err => {
                            this.addFormVisible = false
                            this.loading = false
                            this.$message.error('添加失败，请稍后再试！')
                        })
                    this.$nextTick(() => { that.getdata(this.formInline) })
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
                    deleteVR(row.vrId)
                        .then(res => {
                            if (res.code == 200) {
                                this.$message({
                                    type: 'success',
                                    message: 'VR数据已删除!'
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
            this.editFormVisible = false;
            this.addFormVisible = false;
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

 
 