<template>
	<view>
		<view class="tabs">
			<u-tabs :list="list" :is-scroll="false" :current="current" font-size="30" active-color="#2979ff"
				inactive-color="#606266" @change="change">
			</u-tabs>
		</view>
		<view class="content_first" v-if="current===0">
			<view class="banner">
				<image src="/static/images/政策.jpg" mode="aspectFill"></image>
			</view>
			<view class="policy">
				<view class="item" v-for="(item,index) in policyList" :key="index" @tap="toPolicyDetail(item.policyId)">
					<view class="top">
						<u-section :title="item.releaseTime" sub-title="查看详情" :show-line="false" :bold="false">
						</u-section>
					</view>
					<view class="bottom">
						{{item.title}}
					</view>
				</view>
			</view>
		</view>

		<view class="content_second" v-if="current===1">
			<view class="banner">
				<image src="/static/images/公示.jpg" mode="aspectFill"></image>
			</view>
			<view class="public">
				<view class="item" v-for="(item,index) in publicList" :key="index" @tap="toPublicDetail(item.newsId)">
					<view class=" top">
						<u-section :title="item.releaseTime" sub-title="查看详情" :show-line="false" :bold="false">
						</u-section>
					</view>
					<view class="bottom">
						{{item.title}}
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				list: [{
					name: '政策指引'
				}, {
					name: '公示通知'
				}, ],
				current: 0,
				policyList: [],
				publicList: []
			};
		},
		onShow() {
			this.getPolicy();
			this.getNews();
		},
		methods: {
			change(index) {
				this.current = index;
			},
			toPolicyDetail(id) {
				uni.navigateTo({
					url: `/pages/policy/detail/index?policyId=${id}`
				})
			},

			toPublicDetail(id) {
				uni.navigateTo({
					url: `/pages/policy/public/index?publicId=${id}`
				})
			},

			getPolicy() {
				uni.request({
					url: "http://localhost:8899/policy/getPolicy",
					method: 'POST',
					dataType: 'json',
					data: {},
					success: (res) => {
						console.log(res.data);
						this.policyList = res.data.data.data;
					},
				});
			},

			getNews() {
				uni.request({
					url: "http://localhost:8899/news/getNews",
					method: 'POST',
					dataType: 'json',
					data: {},
					success: (res) => {
						console.log(res.data);
						this.publicList = res.data.data.data;
					},
				});
			},

		},
	}
</script>

<style lang="scss" scoped>
	.tabs {
		margin-top: -15rpx;
		font-weight: bold;
	}

	.content_first {
		height: 500rpx;

		.banner {
			height: 250rpx;
			width: 100%;
			background-color: lightblue;

			image {
				width: 100%;
				height: 100%;
				box-shadow: 0px 12px 20px #001cbe;
			}

		}

		.policy {
			width: 95%;
			height: auto;
			margin: 0 auto;
			display: flex;
			flex-direction: column;
			margin-top: -20rpx;


			.item {
				height: 180rpx;
				width: 100%;
				border-radius: 15rpx;
				background-color: #FFFFFF;
				margin-bottom: 20rpx;
				padding: 15rpx;
				z-index: 1;

				.top {
					height: 50%;
					padding: 15rpx;
					border-bottom: 1rpx solid lightgrey;
				}

				.bottom {
					height: 50%;
					padding: 15rpx;
					margin-top: 5rpx;
					font-size: 32rpx;
					font-family: Arial, Helvetica, sans-serif;

				}
			}
		}


	}


	.content_second {
		.banner {
			height: 250rpx;
			width: 100%;
			background-color: lightblue;

			image {
				width: 100%;
				height: 100%;
				box-shadow: 0px 12px 20px #868cb5;
			}
		}

		.public {
			width: 95%;
			height: auto;
			margin: 0 auto;
			display: flex;
			flex-direction: column;
			margin-top: -20rpx;


			.item {
				height: 200rpx;
				width: 100%;
				border-radius: 15rpx;
				background-color: #FFFFFF;
				margin-bottom: 20rpx;
				padding: 15rpx;
				z-index: 1;

				.top {
					height: 35%;
					padding: 15rpx;
					border-bottom: 1rpx solid lightgrey;
				}

				.bottom {
					height: 100rpx;
					padding: 15rpx;
					margin-top: 15rpx;
					font-size: 32rpx;
					font-weight: bold;
					font-family: Arial, Helvetica, sans-serif;
					text-overflow: ellipsis;
					display: -webkit-box;
					-webkit-line-clamp: 2; // 控制多行的行数
					-webkit-box-orient: vertical;
					overflow: hidden;

				}

			}
		}

	}
</style>
