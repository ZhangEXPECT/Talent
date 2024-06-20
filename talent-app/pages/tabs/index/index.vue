<template>
	<view class="index">
		<view class="top" :style="{ top: statusBarTop }">
			<!-- 搜索 -->
			<view class='notice'>
				<view class='serch'>
					<navigator url="/pages/map/map" style="text-align: center;border-right: 1px solid #f1f1f1;">
						<text style="font-size: 29rpx; margin:0 10rpx;">{{cityData}}</text>
						<text class="iconfont icon-xiangxia" style="font-size: 24rpx;"></text>
					</navigator>
					<navigator url="/pages/house/search/index" class="input" hover-class="none"><text
							class="iconfont icon-xiazai5"></text>
						<text style="margin-left:8rpx;color: #ccc;font-size: 30rpx;letter-spacing:5rpx">搜索公寓</text>
					</navigator>

				</view>
				<view class="message" @tap="toMessage">
					<image src="/static/images/消息.png" mode="aspectFit"></image>
				</view>

			</view>


			<!-- 轮播图 -->
			<view v-if="imgUrls.length" class="swiper">
				<swiper :autoplay="true" :interval="3000" :duration="1000" :circular="true">
					<block v-for="(item,index) in imgUrls" :key="index" style="width: 100%;">
						<swiper-item style="width: 100%;">
							<navigator :url='item.url' class='slide-navigator acea-row row-between-wrapper'
								hover-class='none'>
								<image :src="item.pic" mode="true" class="slide-image" lazy-load></image>
							</navigator>
						</swiper-item>
					</block>
				</swiper>
			</view>
		</view>

		<!-- 菜单 -->
		<view class="menu">
			<view class='nav acea-row' v-if="menus.length">
				<block v-for="(item,index) in menus" :key="index">
					<navigator class='item' :url='item.url' hover-class='none'>
						<view class='pictrue'>
							<image :src='item.pic' mode="aspectFill"></image>
						</view>
						<view class="menu-txt">{{item.name}}</view>
					</navigator>
				</block>
			</view>
		</view>

		<view class="notice">
			<u-notice-bar type="none" :is-circular="true" mode="vertical" padding="16rpx 35rpx" :list="list">
			</u-notice-bar>
		</view>

		<view class="center">
			<view class="first">
				<image src="/static/images/banner1.png" mode="aspectFit"></image>
			</view>
			<view class="left">
				<view class="second">
					<image src="/static/images/banner2.jpg" mode="aspectFill"></image>
				</view>
				<view class="bottom">
					<!-- 					<view class="third">
						<image src="" mode="aspectFit"></image>
					</view>
					<view class="forth">
						<image src="/static/images/core.png" mode="aspectFill"></image>
					</view> -->
					<image src="/static/images/core.png" mode="aspectFill"></image>

				</view>
			</view>

		</view>
		<view class="house">
			<u-section title="今日热门" :right="false" font-size="33"></u-section>
			<view class="houseList">
				<view class="item" v-for="(item,index) in houseList" :key="index">
					<image :src="item.img.img1" mode="aspectFill" @tap.native="toHouseDetail(item.houseId)"></image>
					<view class="detail">
						<view class="name">
							{{item.cellName}}
						</view>
						<view class="district">
							<text>{{item.address}}</text>
							<text>/{{item.houseArea}}套</text>
						</view>
						<view class="advantage">
							<view class="itemn" v-for="itemn in item.advantage" :key="itemn.index">
								{{itemn}}
							</view>
						</view>
						<view class="price">
							{{item.housePrice*10-500}}
							<block v-if="item.housePrice">{{-item.housePrice*10}}</block> 元/月
						</view>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	var statusBarHeight = uni.getSystemInfoSync().statusBarHeight + 'px';
	var statusBarTop = uni.getSystemInfoSync().statusBarHeight + 'rpx';
	export default {
		data() {
			return {
				statusBarHeight: statusBarHeight,
				statusBarTop: statusBarTop,
				list: [
					'人才资讯最新知',
					'最新人才公寓入住指南',
				],
				houseList: [],
				cityData: '南昌市',
				imgUrls: [{
						url: '',
						pic: 'https://www.zhijijiaoyu.com/uploads/image/20210706/1625528610258_2.jpeg'
					},

					{
						url: '',
						pic: 'https://tse4-mm.cn.bing.net/th/id/OIP-C.eGsm0bz9jLYaz801nEoOlQHaDJ?w=327&h=148&c=7&r=0&o=5&dpr=1.4&pid=1.7'
					},
					{
						url: '',
						pic: 'https://ts1.cn.mm.bing.net/th/id/R-C.b4aa403c63e210a296092933c38d8aa7?rik=07q2H6zaLe6WIw&riu=http%3a%2f%2fwww.zaoxu.com%2fuploadfile%2fimgall%2f22dcc451da81cb39db35861887dd160924aa183098.jpg&ehk=pSwWrpEfinLiwfNuLnvqMS7AblaC4duYr49Z22bco2I%3d&risl=&pid=ImgRaw&r=0'
					},
				],
				menus: [{
						url: '/pages/house/qualification/index',
						pic: '/static/images/人才公寓.png',
						name: '公寓申请'
					},
					{
						url: '/pages/subsidy/index',
						pic: '/static/images/笔.png',
						name: '补贴申请'
					},
					{
						url: '/pages/progress/index',
						pic: '/static/images/进度.png',
						name: '进度查询'
					},
					{
						url: '/pages/policy/index',
						pic: '/static/images/政策.png',
						name: '政策通知'
					},
				]

			};
		},
		onShow() {
			this.getAllHouse();
		},
		methods: {

			getAllHouse() {
				uni.request({
					url: "http://localhost:8899/house/getHouse",
					method: 'get',
					dataType: 'json',
					success: (res) => {
						console.log(res.data);
						this.houseList = res.data.data;
						console.log(this.houseList);
						this.houseList.forEach((item) => {
							item.advantage = ['电梯房', '停车位', '充电桩', '便利店']
						});
					},
				});
			},
			toHouseDetail(id) {
				uni.navigateTo({
					url: `/pages/house/index?houseId=${id}`
				})
			},

			// 消息
			toMessage() {

			},
		},
	}
</script>

<style lang="scss" scoped>
	.index {
		background-color: #FFFFFF;
	}

	.top {
		height: auto;
		width: 100%;
		position: relative;
		// top: 170rpx;
		// margin-top: -23rpx;
		margin-bottom: 25rpx;
		overflow: hidden;
		background-image: linear-gradient(175deg, #359efa, #FFFFFF);


		.notice {
			margin-left: 25rpx;
			width: 80%;
			height: 70rpx;
			border-radius: 35rpx;
			background-color: #ffffff;
			margin-bottom: 10rpx;
			margin-top: 20rpx;
			line-height: 63rpx;
			padding: 5rpx 14rpx;
			position: relative;
			z-index: 2;
			display: flex;

			.serch {
				width: 80%;
				display: flex
			}
		}

		.message {
			width: 70rpx;
			height: 70rpx;
			position: absolute;
			right: -100rpx;

			image {
				width: 100%;
				height: 100%;
			}
		}

		.swiper {
			position: relative;
			width: 94%;
			height: 500rpx;
			margin: 0 auto;
			border-radius: 30rpx;
			overflow: hidden;
			margin-bottom: 25rpx;
			margin-top: 20rpx;

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

				.dec {
					width: 100%;
					position: absolute;
					bottom: 0;
					left: 0;
					height: 150rpx;
					background-color: white;
					z-index: 30;

					.title {
						position: relative;
						top: 25rpx;
						width: 450rpx;
						font-size: 36rpx;
						line-height: 1;
						font-weight: 500;
						font-family: math;
						margin-left: 50rpx;
						overflow: hidden;
						text-overflow: ellipsis;
						white-space: nowrap;
					}

					.botto {
						display: flex;
						width: 100%;
						height: 50rpx;
						margin-left: 50rpx;

					}

					.index {
						position: absolute;
						right: 50rpx;
						font-size: 25rpx;
						color: #8a8a8a;
						padding-top: 40rpx;
					}
				}

				.active {
					position: relative;
					opacity: 1;
					// z-index: 25;
					top: 5%;
					transition: all 0s ease-in-out 0s;
					// transform: translateY(615rpx);
					transform: scale(0.9, 0.9);
				}
			}

		}

	}

	.menu {
		height: 160rpx;
		width: 95%;
		margin: 0rpx auto;
		border-radius: 15rpx;
		margin-bottom: 15rpx;

		.nav {
			padding-bottom: 26rpx;
			margin-bottom: 15rpx;
			opacity: 1;
			border-radius: 14rpx;
			width: 100%;


			.item {
				display: flex;
				flex-direction: column;
				align-items: center;
				justify-content: center;
				width: 25%;
				margin-top: 30rpx;

				image {
					width: 80rpx;
					height: 80rpx;
				}
			}
		}
	}

	.notice {
		background-color: #ebebeb;
		border-radius: 25rpx;
		width: 95%;
		margin: 0rpx auto;
		margin-bottom: 15rpx;
	}

	.center {
		height: 400rpx;
		padding: 10rpx;
		width: 95%;
		margin: 0rpx auto;
		border-radius: 15rpx;
		display: flex;
		justify-content: space-around;

		.first {
			width: 45%;
			height: 100%;
			overflow: hidden;



			image {
				width: 100%;
				height: 100%;
				border-radius: 15rpx;
				border: 4rpx solid #f0dfab;
			}
		}

		.left {
			width: 50%;
			height: 50%;

			.second {
				width: 100%;
				height: 100%;

				image {
					width: 100%;
					height: 70%;
					border-radius: 15rpx;
					border: 3rpx solid #b9d1de;

				}
			}

			.bottom {
				width: 100%;
				height: 100%;
				display: flex;
				flex-direction: row;
				justify-content: space-around;


				image {
					width: 120%;
					height: 100%;
					border-radius: 15rpx;
					margin-top: -15rpx;
				}




			}

		}




	}

	.house {
		margin-top: 50rpx;
		padding: 20rpx;

	}

	.house .houseList {
		margin-top: 15rpx;
		padding: 5rpx;

		.item {
			height: 300rpx;
			// background-color: lightyellow;
			margin: 10rpx 10rpx;
			// padding: 20rpx;
			display: flex;
			align-items: center;
			border-bottom: 1rpx solid lightgrey;

			image {
				height: 80%;
				width: 300rpx;
				border-radius: 25rpx;
			}

			.detail {
				margin-left: 20rpx;
				height: 80%;
				width: 350rpx;
				padding: 2rpx;
				display: flex;
				flex-direction: column;

				.name {
					font-size: 38rpx;
					font-family: 'Times New Roman', Times, serif;
					font-weight: bold;
				}

				.district {
					margin: 15rpx 0 20rpx 0;
				}

				.advantage {
					width: 100%;
					height: 80rpx;
					text-align: center;
					display: flex;
					flex-direction: row;
					flex-flow: row wrap;


					.itemn {
						background-color: lightgrey;
						font-size: 18rpx;
						width: 100rpx;
						border-radius: 15rpx;
						margin: 2rpx 5rpx;

					}
				}

				.price {
					margin-top: 20rpx;
					font-size: 35rpx;
					font-weight: bold;
					color: #3491fa;
				}
			}
		}
	}
</style>
