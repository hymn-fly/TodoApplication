<template>
	<v-container fluid style="width: 40%">
		<v-form ref="form" lazy-validation>
			<v-text-field
				v-model="email"
				:rules="emailRules"
				label="E-mail"
				required
			></v-text-field>
			<v-text-field v-model="password" label="Password" required></v-text-field>
		</v-form>
		<v-row justify="end">
			<v-btn color="success" class="mr-4" @click="login(email, password)" right>
				로그인
			</v-btn>
		</v-row>
		<v-row justify="end">
			<a href="/#/sign-up" class="text-decoration-none" style="float: right">
				계정이 없습니까? 여기서 가입 하세요.
			</a>
		</v-row>
	</v-container>
</template>

<script>
import { TodoApi } from '@/lib/todo-api';

export default {
	name: 'LoginComponent',
	data() {
		return {
			email: '',

			password: '',

			emailRules: [
				v => !!v || 'E-mail is required',
				v => /.+@.+\..+/.test(v) || 'E-mail must be valid',
			],
		};
	},

	methods: {
		login(email, password) {
			const userDto = {
				email,
				password,
			};

			TodoApi.SignIn(userDto);
		},
	},
};
</script>

<style></style>
