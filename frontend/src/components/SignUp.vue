<template>
	<v-container class="grey lighten-5" fluid style="width: 50%">
		<v-row no-gutters justify="start">
			<h1>계정 생성</h1>
		</v-row>
		<v-form>
			<v-text-field
				label="유저 이름*"
				v-model="username"
				outlined
				dense
			></v-text-field>
			<v-text-field
				label="이메일 주소*"
				v-model="email"
				outlined
				dense
			></v-text-field>
			<v-text-field
				label="패스워드*"
				v-model="password"
				outlined
				dense
			></v-text-field>
		</v-form>
		<v-btn block color="primary" @click="signUp">계정 생성</v-btn>
		<a href="/#/login" class="text-decoration-none" style="float: right">
			이미 계정이 있습니까? 로그인 하세요.
		</a>
	</v-container>
</template>

<script>
import { TodoApi } from '@/lib/todo-api';

export default {
	name: 'SignUp',

	data() {
		return {
			username: '',
			email: '',
			password: '',
		};
	},

	methods: {
		signUp() {
			const userCreateRequest = {
				username: this.username,
				email: this.email,
				password: this.password,
			};
			console.log(`User 생성 요청 : ${JSON.stringify(userCreateRequest)}`);
			TodoApi.SignUp(userCreateRequest);
		},
	},
};
</script>

<style></style>
