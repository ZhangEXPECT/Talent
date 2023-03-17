<template>
	<view>
		<view class="">

			<view class="tabs">
				<u-tabs :list="list" :is-scroll="false" :current="current" font-size="5rpx" bg-color=""
					@change="change"></u-tabs>
			</view>
			<view class="content">
				<view class="banner">
					<image src="" mode="aspectFit"></image>
				</view>
				<view class="policy">
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
					name: '资讯通知'
				}, ],
				current: 0
			};
		},
		methods: {
			change(index) {
				this.current = index;
			}
		},
	}
</script>

<style lang="scss" scoped>
	.banner {
		height: 250rpx;
		width: 100%;
		background-color: lightblue;

	}

	.tabs {
		margin-top: -15rpx;
	}
</style>
