<template>
    <div class="content-box">
        <div class="container">
            <!-- 根据ID搜索房源 -->
            <el-row>
                <el-input class="input" placeholder="请输入编号" v-model="searchID" clearable>
                </el-input>
                <el-button @click="queryHouseById(searchID)">搜索</el-button>
                <el-dialog :visible.sync="dialogSearchVisible" :modal-append-to-body='false'>
                    <el-descriptions title="查询结果" direction="vertical" :column="4" border>
                        <el-descriptions-item label="小区">{{ searchHouse.cellName }}</el-descriptions-item>
                        <el-descriptions-item label="城市">{{ searchHouse.city }}</el-descriptions-item>
                        <el-descriptions-item label="地区" :span="2">{{ searchHouse.address }}</el-descriptions-item>
                        <el-descriptions-item label="用户编号">{{ searchHouse.clientId }}</el-descriptions-item>
                        <el-descriptions-item label="员工编号">{{ searchHouse.empId }}</el-descriptions-item>
                        <el-descriptions-item label="房源编号" :span="2">{{ searchHouse.houseId }}</el-descriptions-item>
                        <el-descriptions-item label="面积（㎡）">{{ searchHouse.houseArea }}</el-descriptions-item>
                        <el-descriptions-item label="价格(万元)">{{ searchHouse.housePrice }}</el-descriptions-item>
                        <el-descriptions-item label="朝向" :span="2">{{ searchHouse.houseOrientation }}
                        </el-descriptions-item>
                        <el-descriptions-item label="类型">
                            <el-tag size="small">{{ searchHouse.houseType }}</el-tag>
                        </el-descriptions-item>
                        <el-descriptions-item label="状态">{{ searchHouse.houseStatus }}</el-descriptions-item>
                        <el-descriptions-item label="户型">{{ searchHouse.layoutType }}</el-descriptions-item>
                    </el-descriptions>
                </el-dialog>
            </el-row>
            <!-- 房源信息 -->
            <el-table :data="houseData.slice((currentPage - 1) * pagesize, currentPage * pagesize)" border
                highlight-current-row :header-cell-style="{ 'text-align': 'center' }"
                :cell-style="{ 'text-align': 'center' }" style="width: 100%; margin-top: 8px; text-align: center">
                <el-table-column prop="houseId" label="房源编号" width="90">
                </el-table-column>
                <el-table-column prop="clientId" label="客户编号" width="90">
                </el-table-column>
                <el-table-column prop="city" label="城市" width="90">
                </el-table-column>
                <el-table-column prop="address" label="地址" width="100">
                </el-table-column>
                <el-table-column prop="cellName" label="小区" width="160">
                </el-table-column>
                <el-table-column prop="houseArea" label="面积（㎡）" width="90" sortable>
                </el-table-column>
                <el-table-column prop="housePrice" label="价格(万元)" width="90" sortable>
                </el-table-column>
                <el-table-column prop="layoutType" label="户型" width="120">
                </el-table-column>
                <el-table-column prop="houseStatus" label="状态" width="80"
                    :filters="[{ text: '已售', value: '已售' }, { text: '在售', value: '在售' }]" :filter-method="filterTag"
                    filter-placement="bottom-end">
                    <template slot-scope="scope">
                        <el-tag :type="scope.row.houseStatus === '已售' ? 'primary' : 'success'" disable-transitions>
                            {{ scope.row.houseStatus }}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column prop="houseType" label="房源类型" width="80" sortable>
                </el-table-column>
                <el-table-column prop="houseOrientation" label="朝向" width="80">
                </el-table-column>
                <el-table-column fixed="right" label="操作" width="225">
                    <template slot-scope="scope">
                        <!-- 查看房源图片 -->
                        <el-button @click="getHouseImg(scope.row)" size="small">查看</el-button>
                        <el-dialog title="房源图片" :visible.sync="dialogImgVisible" :modal-append-to-body='false'>
                            <div class="imgArea">
                                <div class="block">
                                    <el-carousel trigger="click" height="400px">
                                        <el-carousel-item v-for="item in house_Img" :key="item.id">
                                            <img :src="item" alt="" />
                                        </el-carousel-item>
                                    </el-carousel>
                                </div>
                            </div>
                        </el-dialog>
                        <!-- 修改房源信息 -->
                        <el-button @click="updataHouse(scope.row)" size="small">修改</el-button>&nbsp;
                        <el-dialog title="修改信息" :visible.sync="dialogVisible" width="30%" :modal-append-to-body='false'>
                            <el-form ref="updateform" :model="update_House" label-width="80px">
                                <el-form-item label="城市">
                                    <el-input v-model="update_House.city"></el-input>
                                </el-form-item>
                                <el-form-item label="地址">
                                    <el-input v-model="update_House.address"></el-input>
                                </el-form-item>
                                <el-form-item label="小区">
                                    <el-input v-model="update_House.cellName"></el-input>
                                </el-form-item>
                                <el-form-item label="面积（㎡）">
                                    <el-input v-model="update_House.houseArea"></el-input>
                                </el-form-item>
                                <el-form-item label="价格(万元)">
                                    <el-input v-model="update_House.housePrice"></el-input>
                                </el-form-item>
                                <el-form-item label="户型">
                                    <el-input v-model="update_House.layoutType"></el-input>
                                </el-form-item>
                                <el-form-item label="类型">
                                    <el-radio-group v-model="update_House.houseType">
                                        <el-radio label="新房"></el-radio>
                                        <el-radio label="二手房"></el-radio>
                                    </el-radio-group>
                                </el-form-item>
                                <el-form-item label="朝向">
                                    <el-select v-model="update_House.houseOrientation" placeholder="请选择房源朝向">
                                        <el-option label="南" value="南"></el-option>
                                        <el-option label="东" value="东"></el-option>
                                        <el-option label="北" value="北"></el-option>
                                        <el-option label="西" value="西"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-form>
                            <div slot="footer" class="dialog-footer">
                                <el-button @click="dialogVisible = false">取 消</el-button>
                                <el-button type="primary" @click="updateConfirm()">确 定</el-button>
                            </div>
                        </el-dialog>
                        <!-- 删除房源信息 -->
                        <el-button @click="delHouse(scope.row)" type="danger" size="small">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <el-pagination @size-change="size_change" @current-change="current_change" :current-page="currentPage"
                :page-sizes="[9, 20, 30]" :page-size="pagesize" layout="total, sizes, prev, pager, next, jumper" :total="houseData.length
                ">
            </el-pagination>
        </div>
    </div>
</template>
<script>
export default {
    data() {
        return {
            searchID: "",
            searchHouse: {
                address: "",
                adminId: "",
                cellName: "",
                city: "",
                clientId: "",
                empId: "",
                houseArea: "",
                houseId: "",
                houseOrientation: "",
                housePrice: "",
                houseType: "",
                image: null,
                layoutType: "",
            },
            update_House: "",   //修改房源
            house_Img: [],   //查看房源图片
            houseData: [],
            houseID: "",
            house: {
                address: "",
                adminId: "",
                cellName: "",
                city: "",
                clientId: "",
                empId: "",
                houseArea: "",
                houseId: "",
                houseOrientation: "",
                housePrice: "",
                houseType: "",
                image: null,
                layoutType: "",
            },
            total: 0,//总条目数
            pagesize: 9,//每页显示条目个数
            currentPage: 1,//当前页数
            dialogSearchVisible: false,  //房源查询结果弹出框
            dialogVisible: false,       //修改表单弹出框
            dialogImgVisible: false,     //房源图片弹出框
        }
    },
    created() {
        //自动获取房源信息
        this.getHouseData()
    },
    methods: {
        //房源状态筛选
        filterTag(value, row) {
            return row.houseStatus === value;
        },
        //监听 pagesize 改变的事件
        size_change(newSize) {
            this.pagesize = newSize
        },
        //监听 页码值 改变的事件
        current_change(newPage) {
            this.currentPage = newPage
        },

        //获取房源信息
        getHouseData() {
            this.$axios({
                url: "/house/getHouse",//请求的后台接口
                method: "get",//get请求方式
            }).then(response => {
                console.log(response)
                this.houseData = response.data.data
            }).catch(error => {
                //请求失败
            });
        },

        //根据ID搜索房源
        queryHouseById() {
            console.log(this.searchID)
            this.$axios.get(`/house/queryById/${this.searchID}`).then((request => {
                console.log(request.data.data);
                this.searchHouse = request.data.data
                console.log(this.searchHouse);
                this.dialogSearchVisible = true
                this.$message({
                    showClose: true,
                    message: '查询成功',
                    type: 'success'
                });
            })).catch((error) => {
                console.log(error);
            })
            this.searchID = ""
        },

        //查看房源图片
        getHouseImg(house) {
            this.house_Img = house.img
            console.log(this.house_Img);
            this.dialogImgVisible = true;
            delete this.house_Img.imgId
        },

        //修改房源
        updataHouse(house) {
            this.update_House = house;
            console.log(this.update_House);
            this.dialogVisible = true;
        },

        //修改提交
        updateConfirm() {
            this.$axios({
                url: "/house/update",
                method: "post",
                data:
                    this.update_House,
                header: {
                    'Content-Type': 'application/json;charset=UTF-8'
                }
            }).then(res => {
                if (res.data != null) {
                    this.dialogVisible = false;
                };
                console.log(res)
                this.$message({
                    showClose: true,
                    message: '修改成功！',
                    type: 'success'
                });
            })
        },

        //删除房源
        delHouse(house) {
            this.houseID = house.houseId
            console.log(this.houseID)
            this.$confirm('此操作将永久删除该房源, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            }).then(() => {
                this.$axios.get(`/house/delete/${this.houseID}`).then((request => {
                    console.log(request.data.message)
                    if (request.data.message === "删除成功") {
                        this.$message({
                            showClose: true,
                            type: 'success',
                            message: '删除成功!'
                        });
                        this.getHouseData()
                    } else {
                        this.$message({
                            showClose: true,
                            message: '删除失败，该房源存在订单！',
                            type: 'warning'
                        });
                    }
                })).catch((error) => {
                })
            }).catch(() => {
                this.$message({
                    showClose: true,
                    type: 'info',
                    message: '已取消删除'
                });
            });
        },
    }
}
</script>

<style scoped>
.input {
    width: 200px;
}
</style>