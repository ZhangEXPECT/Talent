<template>
	<view class="content">

		<view class="top">
			<view class="header">
				<view class="iconfont icon-shezhi"></view>
				<view class="iconfont icon-xiaoxi"></view>
			</view>

			<view class="user">
				<view class="img">
					<image src="/static/images/用户.png" mode="aspectFill"></image>
				</view>
				<view class="dec">
					<text class="phone" @click="toStoreIndex">{{userDetail.phone}}</text>
					<view :class="userDetail.isCertified?'active':'default'"></view>
				</view>
				<view class="edit">
					点击编辑资料
				</view>
			</view>

		</view>


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

		<view class="title">
			<u-section title="基础信息" :right="false" font-size="38"></u-section>
			</u-section>
		</view>
		<view class="bottom">
			<view class="item">
				<text>合同管理</text>
				<view class="name" @click="chat">
					<text class="iconfont icon-lianxikefu"></text>
					{{}}
				</view>
			</view>
			<view class="item">
				<text>入住信息</text>
				<view class="address">
					{{}}
				</view>
			</view>
			<view class="item">
				<text>报修管理</text>
				<view class="assurance">
					<image src="@/static/images/assurance.png" mode=""></image>
					{{}}
				</view>

			</view>

			<view class="item">
				<text>资质证照</text>
				<view class="certificate" @tap="showCertification">
					<image src="@/static/images/certificate.png" mode=""></image>
					<!-- {{storeDec.certificate}} -->
					<text class="iconfont icon-xiangyou"></text>
				</view>
			</view>

			<view class="item">
				<text>常见问题</text>
				<view class="card" @tap="showCard">
					<text class="iconfont icon-xiangyou"></text>
				</view>
			</view>
			<view class="item">
				<text>我要咨询</text>
				<view class="card" @tap="showCard">
					<text class="iconfont icon-xiangyou"></text>
				</view>
			</view>
		</view>

		<view class="logout">
			<text>退出登录</text>

		</view>
	</view>
</template>

<script>
	var statusBarHeight = uni.getSystemInfoSync().statusBarHeight + 'px';
	var statusBarTop = uni.getSystemInfoSync().statusBarHeight + 'px';
	export default {
		data() {
			return {
				statusBarHeight: statusBarHeight,
				statusBarTop: statusBarTop,
				userId: 1,
				userDetail: {
					avatar: '',
					phone: 13333333333,
					isCertified: true,

				},
				userDec: {
					name: '某某旗舰店',
					address: '江苏-苏州',
					assurance: '1000',
					certificate: '',
					card: '',
				},
				menus: [{
						url: '/pages/house/application/index',
						pic: '/static/images/service/我的申请.png',
						name: '我的申请'
					},
					{
						url: '/pages/house/qualification/index',
						pic: '/static/images/service/预约看房.png',
						name: '预约看房'
					},
					{
						url: '/pages/progress/index',
						pic: '/static/images/service/房源收藏.png',
						name: '我的收藏'
					},
					{
						url: '/pages/policy/index',
						pic: '/static/images/service/费用账单.png',
						name: '费用账单'
					}
				]
			};
		},
		onLoad: function(options) {},
		onShow: function() {},

		methods: {
			connectGoEasy() {

			},

			chat() {

			},

			getMerDetail: function() {

			},

			formatNumber(num) {

			},

			subscribe: function() {

			},
			getAllSubscribe: function() {

			},

			toStoreIndex() {
				uni.navigateTo({
					url: `/pages/stores/store/index?storeId=${this.storeId}`
				});
			},

			showCertification() {

			},
			showCard() {

			},
		},
	}
</script>

<style lang="scss" scoped>
	.temp {
		margin-bottom: 100rpx;
	}

	.u-card-wrap {
		background-color: $u-bg-color;
		padding: 1px;
	}

	.u-body-item {
		font-size: 32rpx;
		color: #333;
		padding: 20rpx 10rpx;
	}

	.content {
		height: auto;
		width: 100%;
		background-image: linear-gradient(to right, #9de5fe, #E3F4F9);
		position: absolute;
	}

	.content .top {

		height: 420rpx;
		width: 100%;
		background-image: url('https://bpic.51yuansu.com/backgd/cover/00/59/28/5c2779441874a.jpg?x-oss-process=image/resize,h_360,m_lfit/sharpen,100');
		background-repeat: no-repeat;
		background-position: center;
		background-size: cover;
	}

	.content .top .header {

		height: 80rpx;
		width: 100%;
		padding: 25rpx;
		display: flex;
		justify-content: flex-end;


		.iconfont {
			font-size: 55rpx;
			font-weight: bold;
			margin-left: 25rpx;
		}
	}

	.content .top .user {
		height: 200rpx;
		background-color: rgba(0, 0, 0, 0);
		width: 85%;
		display: flex;
		border-bottom: 1rpx solid lightgrey;
		margin: 0 auto;
		// margin-top: 25rpx;
		// margin-left: 25rpx;			

		.img {

			height: 150rpx;
			width: 150rpx;
			background-color: #FFFFFF;
			border-radius: 50%;
			margin-top: 25rpx;
			margin-left: 25rpx;

			image {
				width: 100%;
				height: 100%;
			}
		}

		.dec {
			// position: relative;
			// height: 200rpx;
			// width: 73%;
			// margin-left: 25rpx;

			.phone {
				display: -webkit-flex;
				width: auto;
				height: 80rpx;
				line-height: 1;
				font-weight: bold;
				padding-top: 35rpx;
				font-size: 40rpx;
				align-items: center;
			}

			.active {}

			.default {}

		}

		.edit {
			height: 48rpx;
			width: 190rpx;
			background-color: #99c7fc;
			border-radius: 25rpx 0 0 25rpx;
			position: absolute;
			right: 0;
			top: 180rpx;
			color: #FFFFF4;
			font-size: 28rpx;
			font-weight: bold;
			text-align: center;
		}


	}


	.content .menu {

		height: 150rpx;
		width: 90%;
		margin: 0rpx auto;
		border-radius: 20rpx;
		margin-bottom: 15rpx;
		margin-top: -100rpx;
		background-color: #FFFFFF;
		box-shadow: 8rpx 12rpx 20rpx #91bcee;

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
				margin-top: 35rpx;

				image {
					width: 60rpx;
					height: 60rpx;
				}

				.menu-txt {
					font-size: 25rpx;
				}
			}
		}
	}

	.content .title {
		position: relative;
		height: 50rpx;
		width: 100%;
		padding: 30rpx;
		margin-top: 15rpx;
		font-size: 32rpx;
		margin-bottom: 30rpx;

	}


	.content text {
		font-size: 32rpx;
		font-weight: bold;
		margin-left: 25rpx;
		margin-top: 25rpx;

	}

	.content .bottom {
		background-color: white;
		width: 93%;
		height: 600rpx;
		margin: auto;
		margin-top: 15rpx;
		border-radius: 20rpx;
		box-shadow: 0px 7px 21px rgba(218, 218, 218, 0.7);
		margin-bottom: 300rpx;

		.item {
			height: 100rpx;
			padding-top: 30rpx;
			border-bottom: 1rpx solid rgba(218, 218, 218, 0.2);

			text {
				font-size: 30rpx;
			}

			.iconfont {
				margin-left: 15rpx;
				font-size: 20rpx;
			}

			image {
				height: 25rpx;
				width: 25rpx;
				margin: 10rpx 15rpx 0 0;
			}

			.name {
				position: relative;
				float: right;
				margin-right: 30rpx;

				.iconfont {
					margin-right: 20rpx;
				}

			}

			.address {
				position: relative;
				float: right;
				margin-right: 30rpx;

			}

			.assurance {
				position: relative;
				float: right;
				margin-right: 30rpx;
			}


			.certificate {
				position: relative;
				float: right;
				margin-right: 30rpx;
				width: 100rpx;
				display: flex;
				padding-left: 30rpx;

				text {
					margin-top: 15rpx;
				}

			}

			.popImg {
				width: 100%;
				height: 100%;
			}

			.popImg image {
				height: 100%;
				width: 100%;
			}

			.card {
				position: relative;

				float: right;
				margin-right: 30rpx;
			}


		}
	}

	.content .logout {
		width: 90%;
		height: 80rpx;
		position: absolute;
		left: 5%;
		bottom: 45rpx;
		background-color: $theme-color;
		border-radius: 30rpx;
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: center;

		text {
			font-size: 35rpx;
			line-height: 1rpx;
			height: 15rpx;
		}
	}
</style>
