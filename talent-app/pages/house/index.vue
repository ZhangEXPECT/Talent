<template>
	<view class="index">
		<!-- 轮播图 -->
		<view class="swiper">
			<swiper :autoplay="true" :interval="3000" :duration="1000" :circular="true" indicator-dots="true"
				indicator-color="white">
				<block v-for="(item,index) in imgUrls" :key="index" style="width: 100%;">
					<swiper-item style="width: 100%;">
						<image :src="item" mode="aspectFill" class="slide-image" lazy-load></image>
					</swiper-item>
				</block>
			</swiper>
		</view>

		<!-- 详情 -->
		<view class="detail">
			<view class="name">
				{{houseDetail.cellName}}
			</view>
			<view class="advantage">
				<view class="item" v-for="item in houseDetail.advantage" :key="item.index">
					{{item}}
				</view>
			</view>
			<view class="price">
				{{houseDetail.housePrice*10-500}}
				<block v-if="houseDetail.housePrice">{{-houseDetail.housePrice*10}}</block> 元/月
			</view>
			<view class="like">

			</view>


			<view class="address">
				<view class="label">
					房源地址:
				</view>
				江西省南昌市青山湖区
			</view>

			<view class="type">
				<view class="label">
					小区户型:
				</view>
				一室一卫
			</view>

			<view class="stock">
				<view class="label">
					房源数量:
				</view>
				85套
			</view>


		</view>



		<view class="dec">
			<text>小区简介</text>
			<u-read-more ref="uReadMore" :shadow-style="shadowStyle" show-height="100" :toggle="true" color="grey">
				<rich-text :nodes="content"></rich-text>
			</u-read-more>
		</view>

		<view class="arround">
			<text>周边及交通</text>
			<u-tabs :list="tabList" :is-scroll="false" :current="current" font-size="30" active-color="#2979ff"
				inactive-color="#606266" @change="change">
			</u-tabs>
			<!-- 未开启定位时状态 -->
			<notGps v-if="showNoGps" />
			<!-- 已开启定位时，正常显示页面 -->
			<view v-else class="page-body">
				<view class="map">
					<map @markertap="markertap" @callouttap='callouttap' style="width: 100%; height: 100%;"
						:latitude="latitude" :longitude="longitude" :scale="scale" :markers="covers">
					</map>
				</view>
			</view>
		</view>

		<view class="display">
			<text>房型展示</text>
			<view class="house">
				<scroll-view scroll-x="true" style="white-space: nowrap; vertical-align: middle;"
					show-scrollbar="false">
					<view class="itemCon" v-for="(item, index) in scrollList" :key="index" @click="houseDetail(item)">
						<view class="item">
							<view class="pictrue">
								<image :src="item.image" mode="aspectFill"></image>
							</view>
							<view class="text lines1">
								<view class="name line1">{{item.type}}</view>
								<view class="price">
									{{item.price}}元/月
								</view>
							</view>
						</view>
						<view class="vr" @tap="toVr">
							<image src="/static/images/vr.png" mode="aspectFit"></image>
						</view>
					</view>
				</scroll-view>
			</view>
		</view>

		<view class="apply" @click="toQualification">
			<button type="default">立即申请</button>
		</view>
	</view>
</template>

<script>
	import {
		getAuthorize
	} from '@/utils/position.js'; //引入位置授权
	import notGps from '@/components/notGPS.vue'; // 引入未开启定位子组件
	import QQMapWX from '@/utils/qqmap-wx-jssdk.js';
	//创建实列
	const getAddress = new QQMapWX({
		key: '3C4BZ-BWA3I-FR7GO-5GQHY-RFTAS-BIFPQ'
	});
	export default {
		components: {
			notGps,
		},
		data() {
			return {
				scrollList: [{
					image: 'https://newoss.zhulong.com/tfs/photo/big/201407/02/1033076guvqttxrpbxsrom.jpg',
					type: '一室一卫',
					size: '25平方米',
					price: '2100'
				}, {
					image: 'https://newoss.zhulong.com/tfs/photo/big/201407/02/1033076guvqttxrpbxsrom.jpg',
					type: '2室一卫',
					size: '50平方米',
					price: '2800'
				}],
				content: '户型方正，分间面积合理，厨房有窗户，采光通风好。建议装修时视窗户大小，重视油烟机的位置...户型方正，分间面积合理，厨房有窗户，采光通风好。建议装修时视窗户大小，重视油烟机的位置...户型方正，分间面积合理，厨房有窗户，采光通风好。建议装修时视窗户大小，重视油烟机的位置户型方正，分间面积合理，厨房有窗户，采光通风好。建议装修时视窗户大小，重视油烟机的位置',
				shadowStyle: {
					backgroundImage: "linear-gradient(-180deg, rgba(255, 255, 255, 0) 0%, #fff 90%)",
					paddingTop: "300rpx",
					marginTop: "-300rpx",
					color: "grey"
				},
				tabList: [{
						name: '交通'
					},
					{
						name: '购物'
					},
					{
						name: '医疗'
					},
					{
						name: '教育'
					}
				],
				current: 0,
				showNoGps: false,
				latitude: 0,
				longitude: 0,
				scale: 13, //地图缩放级别
				covers: [{
						id: 873637,
						latitude: 31.45912, //纬度
						longitude: 128.12925, //经度
						iconPath: '', //显示的图标        
						rotate: 0, // 旋转度数
						width: 20, //宽
						height: 30, //高
						//   title:'我在这里',//标注点名
						alpha: 1, //透明度
						callout: { //自定义标记点上方的气泡窗口 点击有效  
							content: '某某',
							fontSize: 13, //文本大小
							color: '#ffffff',
							borderRadius: 50,
							borderWidth: 1,
							borderColor: '#23abf2',
							bgColor: '#23abf2',
							padding: 12,
							textAlign: 'center',
							display: 'ALWAYS',
						},
					},
					{
						id: 1234597,
						latitude: 31.80000, //纬度
						longitude: 121.72900, //经度
						iconPath: '', //显示的图标        
						rotate: 0, // 旋转度数
						width: 20,
						height: 30,
						//  title:'我在这里',//标注点名
						alpha: 1, //透明度
						callout: { //自定义标记点上方的气泡窗口
							content: '金水区绿地',
							fontSize: 13, //文本大小
							color: '#ffffff',
							borderRadius: 50,
							borderWidth: 1,
							borderColor: '#23abf2',
							bgColor: '#23abf2',
							padding: 12,
							textAlign: 'center',
							display: 'ALWAYS',
						},
					},
					{
						id: 2,
						latitude: 31.05912, //纬度
						longitude: 121.18725, //经度
						iconPath: '', //显示的图标        
						rotate: 0, // 旋转度数
						width: 20, //宽
						height: 30, //高
						alpha: 1, //透明度
						callout: { //自定义标记点上方的气泡窗口 点击有效  
							content: '高新区人才公寓', //文本
							fontSize: 13, //文本大小
							color: '#ffffff',
							borderRadius: 50,
							borderWidth: 1,
							borderColor: '#23abf2',
							bgColor: '#23abf2',
							padding: 12,
							textAlign: 'center',
							display: 'ALWAYS',
						},
					},
					{
						id: 3,
						latitude: 31.45912, //纬度
						longitude: 121.00978, //经度
						iconPath: '', //显示的图标        
						rotate: 0, // 旋转度数
						width: 20, //宽
						height: 30, //高
						alpha: 1, //透明度
						callout: { //自定义标记点上方的气泡窗口 点击有效  
							content: '城厢镇人才公寓', //文本
							fontSize: 13, //文本大小
							color: '#ffffff',
							borderRadius: 50,
							borderWidth: 1,
							borderColor: '#23abf2',
							bgColor: '#23abf2',
							padding: 12,
							textAlign: 'center',
							display: 'ALWAYS',

						},
					},
					{
						id: 4,
						latitude: 31.55912, //纬度
						longitude: 121.02025, //经度
						iconPath: '', //显示的图标        
						rotate: 0, // 旋转度数
						width: 20, //宽
						height: 30, //高
						alpha: 1, //透明度
						callout: { //自定义标记点上方的气泡窗口 点击有效  
							content: '城南区', //文本
							fontSize: 13, //文本大小
							color: '#ffffff',
							borderRadius: 50,
							borderWidth: 1,
							borderColor: '#23abf2',
							bgColor: '#23abf2',
							padding: 12,
							textAlign: 'center',
							display: 'ALWAYS',
						},
					},
					{
						id: 5,
						latitude: 31.85988, //纬度
						longitude: 121.82975, //经度
						iconPath: '', //显示的图标        
						rotate: 0, // 旋转度数
						width: 20, //宽
						height: 30, //高
						alpha: 1, //透明度
						callout: { //自定义标记点上方的气泡窗口 点击有效  
							content: '市中心', //文本
							fontSize: 13, //文本大小
							color: '#ffffff',
							borderRadius: 50,
							borderWidth: 1,
							borderColor: '#23abf2',
							bgColor: '#23abf2',
							padding: 12,
							textAlign: 'center',
							display: 'ALWAYS',
						},
					},

				], //标记点数组

				imgUrls: [{
						url: '',
						pic: 'https://newoss.zhulong.com/tfs/photo/big/201407/02/103302kqgsbe93elyinj9d.jpg?x-oss-process=image/resize,w_760'
					},

					{
						url: '',
						pic: 'https://newoss.zhulong.com/tfs/photo/big/201407/02/1033076guvqttxrpbxsrom.jpg'
					},

				],

				houseDetail: {

				},
				houseId: 0,

			};
		},
		onLoad(options) {
			this.houseId = options.houseId;
			uni.showLoading({
				title: '加载中',
				mask: true,
			});
			//位置授权
			getAuthorize()
				.then(() => {
					this.getSystemInfo();
					uni.hideLoading();
				})
				.catch(() => {
					uni.hideLoading();
					this.getSystemInfo();
				});
		},
		onShow() {
			if (this.showNoGps === true) {
				this.getSystemInfo();
			}
			this.getHouseById(this.houseId);
		},
		methods: {

			toQualification() {
				uni.navigateTo({
					url:"/pages/subsidy/index"
				})
			},

			getHouseById(houseId) {
				uni.request({
					url: `http://localhost:8899/house/queryById/${houseId}`,
					method: 'get',
					dataType: 'json',
					success: (res) => {
						this.houseDetail = res.data.data;
						this.imgUrls = Object.values(res.data.data.img);
						this.imgUrls.shift();
						this.houseDetail.advantage = ['电梯房', '停车位', '充电桩', '健身房', '便利店'];
						console.log(this.houseDetail);
					}
				});
			},

			toVr(id) {
				uni.navigateTo({
					url: `/pages/vr/index?houseId=${id}`
				});
			},
			change(index) {
				this.current = index;
			},

			//获取手机系统信息
			getSystemInfo() {
				let that = this;
				uni.getSystemInfo({
					success: (res) => {
						if (!res.locationEnabled || !res.locationAuthorized) {
							uni.showToast({
								title: '请确保手机系统定位已开启',
								icon: 'none',
								duration: 2000,
							})
							this.showNoGps = true,
								console.log(res),
								console.log(this)
						} else {
							this.getLocation();
						}
					},
				})
			},

			//先获取经纬度，然后再根据经纬度通过腾讯地图获取地址名称
			getLocation() {
				let that = this;
				uni.getLocation({
					type: 'gcj02',
					altitude: false, // 是否返回高度
					accuracy: 'best', // 精度值为20m
					geocode: true,
					success: (res) => {
						// 成功保存地址		
						that.latitude = res.latitude;
						that.longitude = res.longitude;
						console.log("我的地址", that.latitude, that.longitude);
						//标记地点
						var obj = {
							id: 0,
							latitude: res.latitude, //纬度
							longitude: res.longitude, //经度
							iconPath: '', //显示的图标        
							rotate: 0, // 旋转度数
							width: 20, //宽
							height: 30, //高
							alpha: 1, //透明度
							callout: { //自定义标记点上方的气泡窗口 点击有效  
								content: '当前位置', //文本
								fontSize: 13, //文本大小
								color: '#ffffff',
								borderRadius: 50,
								borderWidth: 1,
								borderColor: '#23abf2',
								bgColor: '#23abf2',
								padding: 12,
								textAlign: 'center',
								display: 'ALWAYS',
							}
						};

						// 获取门店信息
						// that.getNearStore(that.latitude,that.longitude);

						//标记点
						that.covers.push(obj);
						console.log(that.covers);
					},
					fail(err) {
						console.log(err, '获取经纬度失败');
						that.showNoGps = true;
					},
					complete() {
						getAddress.reverseGeocoder({
							location: {
								latitude: that.latitude,
								longitude: that.longitude,
							},
							success: function(res) {
								console.log('接口', res);
								let params = {
									province: res.result.ad_info.province,
									city: res.result.address_component.city,
									name: res.result.address_reference.landmark_l2.title,
									lat: that.latitude,
									lng: that.longitude,
								};

								let test = {
									latitude: that.latitude,
									limit: 20,
									longitude: that.longitude,
									page: 1
								}
								console.log(that.getNearStore(test));

								that.addressDetails = params;
								that.list.push('您当前所在位置：' + that.addressDetails.province +
									that.addressDetails
									.city + that.addressDetails.name);
								console.log(that.addressDetails);
								that.showNoGps = false
								//请求接口获取页面数据
								// that.getGoodsList()

							},
							fail: function(res) {
								uni.showToast({
									title: '定位失败',
									duration: 2000,
									icon: 'none'
								});
								console.log(res);
							},
						});


					},
				})
			},


			// 获取附近商场
			getNearStore(data) {
				let that = this;
				console.log(data);
			},

			//地图点击事件
			markertap(e) {
				console.log("点击标签", e);
				let markId = e.mp.detail.markerId;
				// TODO
				// 跳转三维模型页面

			},

			callouttap(e) {
				console.log('点击标点', e);
				let markId = e.mp.detail.markerId;

			},



			// 获取房源详情
			getHouseDetail() {

			},


		},
	}
</script>

<style lang="scss" scoped>
	.index {
		height: auto;
		background-color: #FFFFFF;
	}

	.swiper {
		position: relative;
		width: 100%;
		height: 450rpx;
		margin: 0 auto;
		overflow: hidden;
		margin-bottom: 25rpx;

		swiper {
			height: 100%;

			swiper-item {
				width: 100%;
				height: 668rpx;
			}

			image {
				position: relative;
				width: 100%;
				height: 563rpx;
			}
		}
	}


	.detail {
		height: 400rpx;
		padding: 20rpx 10rpx;
		display: flex;
		flex-direction: column;



		.name {
			font-size: 38rpx;
			font-family: 'Times New Roman', Times, serif;
			font-weight: bold;

		}

		.advantage {
			width: 100%;
			height: 55rpx;
			margin: 8rpx 0rpx;
			display: flex;
			flex-direction: row;
			flex-flow: row wrap;

			.item {
				background-color: lightblue;
				border: 1rpx solid #0c83de;
				color: #0c83de;
				text-align: center;
				width: 120rpx;
				border-radius: 15rpx;
				margin: 5rpx 8rpx 5rpx 0rpx;
			}
		}


		.price {
			margin-top: 35rpx;
			font-size: 38rpx;
			font-weight: bold;
			color: #3491fa;
			padding-bottom: 25rpx;
			border-bottom: 1rpx solid lightgrey;
		}

		.like {
			position: absolute;
			left: 5rpx;
		}

		.address {
			display: flex;
			align-items: center;
			font-weight: bold;
		}

		.type {
			display: flex;
			align-items: center;
			font-weight: bold;
		}

		.stock {
			display: flex;
			align-items: center;
			font-weight: bold;
		}

		.label {
			margin-top: 5rpx;
			padding: 5rpx;
			color: grey;
			margin-right: 15rpx;
		}
	}


	.dec {
		height: auto;
		padding: 6rpx 50rpx 6rpx 50rpx;
		background-color: white;
		margin-top: 6rpx;

		text {
			font-size: 38rpx;
			font-weight: bold;
			margin-left: -40rpx;
			font-family: Georgia, serif;
		}
	}

	.arround {
		height: 450rpx;
		width: 95%;
		margin: 0 auto;
		margin-top: 25rpx;


		text {
			font-size: 38rpx;
			font-weight: bold;
			margin-left: 0rpx;
			font-family: Georgia, serif;
		}

		.map {
			height: 350rpx;
			width: 100%;
		}
	}

	.display {
		margin: 0 auto;
		height: 750rpx;
		width: 95%;
		margin-top: 50rpx;
		margin-bottom: 50rpx;


		text {
			font-size: 38rpx;
			font-weight: bold;
			margin-left: -5rpx;
			font-family: Georgia, serif;
		}

		.house {
			margin-top: 28rpx;
			padding: 10rpx;

			.itemCon {
				display: inline-block;
				width: 85%;
				height: 500rpx;
				margin-right: 24rpx;
				background-color: #f8f8f8;
				border-radius: 15rpx;
			}

			.vr {
				position: relative;
				height: 50rpx;
				width: 50rpx;
				bottom: 465rpx;
				left: 8%;

				image {
					height: 100%;
					width: 100%;
				}
			}

			.item {
				width: 100%;
				height: 100%;

				.pictrue {
					width: 90%;
					height: 70%;
					border-radius: 6rpx;
					padding: 10rpx;
					margin: 0 auto;

					image {
						width: 100%;
						height: 100%;
						border-radius: 6rpx;
						margin-top: 20rpx;
					}
				}

				.text {
					margin-top: 4rpx;
					width: 90%;
					padding: 10rpx;
					margin: 0 auto;

					.name {
						font-size: 30rpx;
						color: #000;
						margin-top: 14rpx;
						font-weight: bold;
					}

					.price {
						margin-top: 35rpx;
						font-size: 32rpx;
						font-weight: bold;
						color: #3491fa;
						padding-bottom: 25rpx;
					}

					.btn {
						width: 220rpx;
						height: 48rpx;
						line-height: 48rpx;
						text-align: center;
						background: linear-gradient(129deg, #FF5555 0%, #FF0000 100%);
						opacity: 1;
						border-radius: 0px 0px 14rpx 14rpx;
						color: #FFFFFF;
						font-size: 26rpx;
						margin-top: 6rpx;
					}
				}
			}
		}
	}

	.apply {
		width: 95%;
		height: 85rpx;
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
			color: white;
		}
	}
</style>
