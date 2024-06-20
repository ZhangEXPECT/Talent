<template>
	<view class="application">
		<view class="title">
			<view class="name">
				<text>入 住 申 请</text>
			</view>
			<view class="banner">
				<text>人才公寓</text>
			</view>
		</view>
		<!-- 进度条 -->
		<view class="step">
			<u-steps :list="numList" :current="step" mode="number" active-color="#3591f8"></u-steps>
		</view>

		<!-- 表单 -->
		<view class="form" v-if="step===0">
			<text>申请人信息</text>
			<u-form :model="userForm" ref="userForm" :error-type="['message']">
				<u-form-item label="姓名" label-width="200" :label-style="label_style" required>
					<u-input v-model="userForm.name" placeholder="请输入姓名" required />
				</u-form-item>
				<u-form-item label="证件类型" label-width="200" :label-style="label_style" required>
					<u-input v-model="userForm.IDType" @click="IDShow=true" placeholder="身份证" />
					<u-select v-model="IDShow" mode="single-column" :list="IDlist" @confirm="confirm"></u-select>
				</u-form-item>
				<u-form-item label="证件号码" label-width="200" :label-style="label_style" required>
					<u-input v-model="userForm.ID" placeholder="请输入证件号" />
				</u-form-item>
				<u-form-item label="出生日期" label-width="200" :label-style="label_style" required>
					<u-input v-model="userForm.birthday" @click="birthShow=true" placeholder="2000-12-19" />
					<u-picker v-model="birthShow" mode="time" @confirm="birthConfirm"></u-picker>
				</u-form-item>
				<u-form-item label="性别" label-width="200" :label-style="label_style" required>
					<u-radio-group v-model="userForm.sex">
						<u-radio @change="sexChange" v-for="(item, index) in sexList" :key="index" :name="item.name"
							:disabled="item.disabled">
							{{item.name}}
						</u-radio>
					</u-radio-group>
				</u-form-item>

				<u-form-item label="是否本地户籍" label-width="200" :label-style="label_style" required>
					<u-radio-group v-model="userForm.local">
						<u-radio @change="localChange" v-for="(item, index) in locaList" :key="index" :name="item.name"
							:disabled="item.disabled">
							{{item.name}}
						</u-radio>
					</u-radio-group>
				</u-form-item>
				<u-form-item label="联系方式" label-width="200" :label-style="label_style" required>
					<u-input v-model="userForm.phone" pachalar />
				</u-form-item>
				<u-form-item label="最高学历" label-width="200" :label-style="label_style" required>
					<u-input v-model="userForm.degree" @click="eduShow=true" placeholder="博士" />
					<u-select v-model="eduShow" mode="single-column" :list="eduList" @confirm="eduConfirm"></u-select>
				</u-form-item>
				<u-form-item label="学位信息" label-width="200" :label-style="label_style">
					<u-input v-model="userForm.degreeInfo" @click="eduShow=true" placeholder="博士" />
					<u-select v-model="eduShow" mode="single-column" :list="eduList" @confirm="degreeConfirm">
					</u-select>
				</u-form-item>
				<u-form-item label="毕业院校" label-width="200" :label-style="label_style" required>
					<u-input v-model="userForm.school" />
				</u-form-item>
				<u-form-item label="所学专业" label-width="200" :label-style="label_style">
					<u-input v-model="userForm.major" />
				</u-form-item>

			</u-form>
		</view>


		<view class="form" v-if="step==1">
			<text>申请人就业信息</text>
			<u-form :model="unitForm" ref="unitForm" :error-type="['message']">
				<u-form-item label="就业情况" label-width="200" :label-style="label_style" required>
					<u-radio-group v-model="unitForm.employment">
						<u-radio @change="localChange" v-for="(item, index) in employList" :key="index"
							:name="item.name" :disabled="item.disabled">
							{{item.name}}
						</u-radio>
					</u-radio-group>
				</u-form-item>

				<block v-if="unitShow">
					<u-form-item label="单位名称" label-width="200" :label-style="label_style" required>
						<u-input v-model="unitForm.unitName" />
					</u-form-item>
					<u-form-item label="统一社会信用代码" label-width="250" :label-style="label_style" required>
						<u-input v-model="unitForm.unitCode" />
					</u-form-item>
					<u-form-item label="单位联系人" label-width="200" :label-style="label_style" required>
						<u-input v-model="unitForm.unitContact" />
					</u-form-item>
					<u-form-item label="联系方式" label-width="200" :label-style="label_style" required>
						<u-input v-model="unitForm.unitPhone" />
					</u-form-item>
				</block>

				<text style="line-height: 60rpx;">居住意向</text>
				<u-form-item label="意向区域" label-width="200" :label-style="label_style" required>
					<u-input v-model="unitForm.intentAera" type="select" @click="showAeraList=true" />
				</u-form-item>
				<u-form-item label="意向小区" label-width="200" :label-style="label_style" required>
					<u-input v-model="unitForm.intentCommunity" type="select" @click="showCommunityList=true" />
				</u-form-item>
				<u-form-item label="意向房型" label-width="200" :label-style="label_style">
					<u-input v-model="unitForm.intentHouseType" type="select" @click="showHouseTypeList=true" />
				</u-form-item>
			</u-form>

			<view class="notice">
				注意： 排队人数随时可能发生变化，不作为候租依据，仅供参
				考！审批通过后将根据申请人的居住意向进行配租，具体楼层、
				朝向、房号等由系统随机分配。申请人不接受随机分配结果或
				未在规定时间内签订租赁合同的，不再享受人才公寓政策。如
				果审批通过后意向房型己满员，则需要申请人按照审批通过先
				后顺序排队侯租，可以点击“人才公寓进度查询”查询排队进度。
			</view>

		</view>


		<view class="form" v-if="step===2">
			<text>附件信息</text>
			<u-form :model="fileForm" ref="fileForm" :error-type="['message']">
				<u-form-item label="毕业证/学位证上传" label-position="top" label-width="300" :label-style="label_style"
					required>
					<u-upload width="160" v-model="fileForm.degreeImg" :show-progress="false" height="160"
						:action="action" :auto-upload="true" @on-remove="onRemove" :file-list="fileList"
						@on-success="successUpload1">
					</u-upload>
				</u-form-item>

				<u-form-item label="学历证明(学信网学历证明/教育部留学学历证明)" label-position="top" label-width="800"
					:label-style="label_style" required>
					<u-upload width="160" v-model="fileForm.educationImg" :show-progress="false" height="160"
						:action="action" :auto-upload="true" @on-remove="onRemove" :file-list="fileList"
						@on-success="successUpload2">
					</u-upload>
				</u-form-item>
				<u-form-item label="其他附件上传" label-position="top" label-width="800" :label-style="label_style" required>
					<u-upload width="160" v-model="fileForm.otherImg" :show-progress="true" height="160"
						:action="action" :auto-upload="true" @on-remove="onRemove" :file-list="fileList"
						@on-success="successUpload3">
					</u-upload>
				</u-form-item>
			</u-form>
			<view class="notice">
				注意：排队人数随时可能发生变化，不作为候租依据，仅供参
				考！审批通过后将根据申请人的居住意向进行配租，具体楼
				层、朝向、房号等由系统随机分配。申请人不接受随机分配结
				果或未在规定时间内签订租赁合同的，不再享受人才公寓政
				策。如果审批通过后意向房型己满员，则需要申请人按照审批
				通过先后顺序排队侯租，可以点击“人才公寓进度查询”查询
				排队进度。
			</view>
		</view>

		<!-- unitForm -->
		<u-action-sheet :list="aeraList" v-model="showAeraList" @click="aeraCallback">
		</u-action-sheet>
		<u-action-sheet :list="communityList" v-model="showCommunityList" @click="communityCallback">
		</u-action-sheet>
		<u-action-sheet :list="houseTypeList" v-model="showHouseTypeList" @click="houseTypeCallback">
		</u-action-sheet>


		<view class="forward" v-if="step==1">
			<button @click="step=0" type="primary">上一步</button>
		</view>
		<view class="forward" v-if="step==2">
			<button @click="step=1" type="primary">上一步</button>
		</view>
		<!-- 下一步 -->
		<view class="next">
			<button @click="submit" type="primary">{{submitStatus}}</button>
		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				rules: {
					name: [{
							required: true,
							message: '请输入姓名',
							// trigger: ['blur', 'change']
						},

						{
							// 自定义验证函数，见上说明
							validator(rule, value, callback) {
								// 上面有说，返回true表示校验通过，返回false表示不通过
								// this.$u.test.mobile()就是返回true或者false的
								var reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
								if (!reg.test(value)) {
									callback(new Error("请输入正确姓名！"));
								} else {
									callback()
								}
							},
							// message: '姓名格式不正确',
							// // 触发器可以同时用blur和change
							// trigger: ['change', 'blur'],
						}
					],

				},
				IDlist: [{
						value: '居民身份证',
						label: '居民身份证'
					},
					{
						value: '临时身份证',
						label: '临时身份证'
					},
					{
						value: '内地通行证',
						label: '内地通行证'
					}
				],
				eduList: [{
						value: '博士',
						label: '博士'
					},
					{
						value: '硕士',
						label: '博士'
					},
					{
						value: '学士',
						label: '博士'
					}
				],
				IDShow: false,
				eduShow: false,
				birthShow: false,
				numList: [{
					name: '申请人信息'
				}, {
					name: '单位信息'
				}, {
					name: '附件信息'
				}],

				step: 0,
				userForm: {
					name: '',
					IDType: '',
					ID: '',
					birthday: '',
					intro: '',
					sex: '',
					local: '',
					phone: '',
					degree: '',
					degreeInfo: '',
					school: '',
					major: '',

				},
				unitForm: {
					employment: 0,
					intentAera: '',
					intentCommunity: '',
					intentHouseType: '',
					unitName: '',
					unitCode: '',
					unitContact: '',
					unitPhone: '',
				},
				fileForm: {
					degreeImg: '',
					educationImg: '',
					otherImg: ''
				},
				action: 'http://localhost:8899/file/upload',
				fileList: [],
				aeraList: [{
						value: '1',
						text: '青山湖区'
					},
					{
						value: '2',
						text: '红谷滩区'
					}
				],
				communityList: [{
						value: '1',
						text: '创业家公寓'
					},
					{
						value: '2',
						text: '高新人才公寓'
					}
				],
				houseTypeList: [{
						value: '1',
						text: '合租房'
					},
					{
						value: '2',
						text: '一室一卫'
					}
				],
				tips: {
					text: '请选择意向区域',
					color: '#909399',
					fontSize: 24
				},
				showAeraList: false,
				showCommunityList: false,
				showHouseTypeList: false,
				locaList: [{
						name: '是',
						disabled: false
					},
					{
						name: '否',
						disabled: false
					}
				],
				sexList: [{
						name: '男',
						disabled: false
					},
					{
						name: '女',
						disabled: false
					}
				],
				employList: [{
						name: '已就业',
						value: 1,
						disabled: false
					},
					{
						name: '未就业',
						value: 0,
						disabled: false
					}
				],
				currentStatus: 0,
				unitShow: false,
				label_style: {
					fontSize: '25rpx',
					fontWeight: 'bold'
				},
				submitStatus: '下一步'
			};
		},
		onShow: function() {

		},
		onLoad: function() {
			uni.showModal({
				title: '温馨提示',
				content: '开始申请前，请准备好如下证明材料原件或照片影像，用于在申请时上传附件资料：' +
					'1.毕业证（必须提供)；  ' +
					'2.学位证（学历为本科以上时必须提供)；  ' +
					'3.现单位劳动合同（已就业人员必须提供，上传内容需包含劳动合同首页、合同期限所在页、双方签字盖章页，本科实习期人员请上传单位实习证明)；  ' +
					'4.学历认证（学历为本科及以上时必须提供学信网学历证书电子注册备案表或教育部留学学历学位认证书)；  ' +
					'5.其他相关资料，如中级职称证书、市级领军人才证明等。',
				success: function(res) {
					if (res.confirm) {
						console.log('用户点击确定');
					} else if (res.cancel) {
						console.log('用户点击取消');
						uni.navigateBack({});
					}
				}
			});
		},
		onReady: function() {
			this.$refs.userForm.setRules(this.rules);
		},

		methods: {

			confirm(e) {
				console.log(e[0].value);
				this.$set(this.userForm, "IDType", e[0].value);
			},
			educonfirm(e) {
				console.log(e[0].value);
				this.$set(this.userForm, "degree", e[0].value);
			},
			degreeConfirm(e) {
				console.log(e[0].value);
				this.$set(this.userForm, "degreeInfo", e[0].value);
			},
			birthConfirm(e) {
				this.$set(this.userForm, "birthday", e.year + '-' + e.month + '-' + e.day);
			},

			// validator: (rule, value, callback) => {
			// 	var reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
			// 	if (!reg.test(value)) {
			// 		callback(console.log(new Error("请输入正确的邮箱地址！")));
			// 	} else {
			// 		callback(console.log('pass'))
			// 	}
			// },
			successUpload1(data, index, name) {
				if (data.code == 200) {
					this.fileForm.degreeImg = data.data;
				}
			},
			successUpload2(data, index, name) {
				if (data.code == 200) {
					this.fileForm.educationImg = data.data;
				}
			},
			successUpload3(data, index, name) {
				if (data.code == 200) {
					this.fileForm.otherImg = data.data;
				}
			},

			localChange(e) {
				let that = this;
				this.employList.map(item => {
					if (item.name == e) {
						that.currentStatus = item.value;
					}
				});
				if (that.currentStatus == 1) {
					this.unitShow = true;
				} else {
					this.unitShow = false;
				}
			},
			sexChange(e) {

			},
			// 点击actionSheet回调

			aeraCallback(index) {
				uni.hideKeyboard();
				this.unitForm.intentAera = this.aeraList[index].text;
			},
			communityCallback(index) {
				uni.hideKeyboard();
				this.unitForm.intentCommunity = this.communityList[index].text;
			},
			houseTypeCallback(index) {
				uni.hideKeyboard();
				this.unitForm.intentHouseType = this.houseTypeList[index].text;
			},

			submit() {
				if (this.step < 2) {
					this.step++;
					this.submitStatus = "下一步";
					console.log(this.currentStatus);
					this.unitForm.employment = this.currentStatus;
					console.log('2', this.unitForm);
					console.log('1', this.userForm);
				}
				if (this.step == 2) {
					this.submitStatus = "提交";
					// console.log('222', this.$refs.uForm.validate());
					// this.validator(this.rules, formName, function() {});
					// this.$refs.userForm.validate(valid => {
					// 	console.log('00', valid);
					// 	if (valid) {
					// 		console.log('验证通过');
					// 	} else {
					// 		console.log('验证失败');
					// 	}
					// });
					let date = new Date().Format("yyyy-MM-dd HH:mm:ss");
					let param = {
						userId:10001,
						buildTime:date,
						status:"待审核",
						remark:"",
						married:"",
						local:'',
						school:this.userForm.school,
						degree:this.userForm.degree,
						degreeInfo:this.userForm.degreeInfo,
						major:this.userForm.major,
						employed:this.unitForm.employment,
					}
					// addQualification() {
					// 	uni.request({
					// 		url: "http://localhost:8899/qualifiaction/add",
					// 		method: 'post',
					// 		dataType: 'json',
					// 		data:param,
					// 		success: (res) => {
					// 			console.log(res.data);
					// 		},
					// 	});
					// }
					console.log('3', this.fileForm);
				}

			}

		},


	}
</script>

<style lang="scss" scoped>
	.application {
		height: auto;
	}

	.title {
		height: 350rpx;
		width: 100%;
		background-image: url('https://img.tukuppt.com/ad_preview/00/03/70/5c98aed22a513.jpg!/fw/980');
		background-repeat: no-repeat;
		background-position: center;
		background-size: cover;
		box-shadow: 8rpx 12rpx 35rpx #91bcee;


		.name {
			height: 150rpx;
			width: 70%;
			background-color: rgba(255, 255, 255, 0.8);
			text-align: center;
			border-radius: 150rpx;
			position: absolute;
			top: 85rpx;
			left: 15%;
			padding: 20rpx;

			text {
				font-size: 80rpx;
				font-weight: bold;
				background: linear-gradient(to right, #3da6f1, #1770c3);
				-webkit-background-clip: text;
				color: transparent;
				font-family: 'Times New Roman', Times, serif;
			}


		}

		.banner {
			height: 80rpx;
			width: 40%;
			background-color: #4f96c5;
			transform: perspective(1.2em) rotateX(-5deg);
			border-radius: 15rpx;
			text-align: center;
			position: absolute;
			top: 240rpx;
			left: 30%;
			padding: 5rpx;

			text {
				font-size: 45rpx;
				font-weight: bold;
				color: white;
				font-family: 'Times New Roman', Times, serif;
			}
		}
	}


	.step {
		height: 170rpx;
		width: 95%;
		margin: 0 auto;
		border-radius: 25rpx;
		background-color: #FFFFFF;
		margin-top: -25rpx;
		padding: 30rpx;
	}

	.form {
		height: auto;
		width: 95%;
		margin: 0 auto;
		border-radius: 25rpx;
		background-color: #FFFFFF;
		margin-top: 25rpx;
		margin-bottom: 120rpx;
		padding: 40rpx;

		text {
			font-size: 35rpx;
			font-weight: bold;
			margin-left: -15rpx;
			font-family: Georgia, 'Times New Roman', Times, serif;
		}

		.notice {
			color: #1770c3;
			height: auto;
			width: 100%;
			font-size: 25rpx;
			font-weight: bold;
			margin-top: 100rpx;
		}
	}

	.next {
		width: 95%;
		height: 90rpx;
		position: relative;
		text-align: center;
		left: 2.5%;
		bottom: 30rpx;
		margin-bottom: 150rpx;

		button {
			width: 100%;
			height: 100%;
			padding: 25rpx;

		}

	}

	.forward {
		width: 95%;
		height: 90rpx;
		position: relative;
		text-align: center;
		left: 2.5%;
		bottom: 30rpx;
		margin-bottom: 50rpx;

		button {
			width: 100%;
			height: 100%;
			padding: 20rpx;
			background-color: #68b5e9;
		}

	}
</style>
