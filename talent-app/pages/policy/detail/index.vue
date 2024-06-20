<template>
	<view>
		<view class="img">
			<image :src="policyDetail.img1" mode="aspectFit"></image>
		</view>
		<view class="content">
			{{policyDetail.content}}
		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				policyId: 0,
				policyDetail: {},
			};
		},
		onLoad(options) {
			this.policyId = options.policyId;
		},
		onShow: function() {
			this.getPolicyById();
		},
		methods: {
			getPolicyById() {
				uni.request({
					url: "http://localhost:8899/policy/queryById/" + this.policyId,
					method: 'GET',
					dataType: 'json',
					success: (res) => {
						console.log(res.data);
						this.policyDetail = res.data.data;
					},
				});
			},
		},

	}
</script>

<style lang="scss">
	
	img{
		height: 50rpx;
		width: 50rpx;
		image{
			width: 100%;
			height: 100%;
		}
	}
	.content {
		text-indent: 30px;
		font-size: 35rpx;
		letter-spacing: 3px;
		line-height: 1.8;

	}
</style>
