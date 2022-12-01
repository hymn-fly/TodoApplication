<template>
	<v-list shaped>
		<v-list-item-group multiple>
			<template v-for="(item, i) in items">
				<v-divider v-if="!item" :key="`divider-${i}`"></v-divider>
				<v-list-item v-else :key="`item-${i}`" :value="item">
					<!-- <template v-slot:default="{ active }"> -->

					<v-list-item-action>
						<v-checkbox
							:input-value="item.done"
							color="deep-purple accent-4"
							@change="checkBoxToggle(item)"
						></v-checkbox>
					</v-list-item-action>

					<v-text-field hide-details="auto" v-text="item.title"></v-text-field>
					<!-- <v-list-item-content v-on:click="test">
              <v-list-item-title v-text="item.title" aria-readonly="false"></v-list-item-title>
            </v-list-item-content> -->

					<v-icon v-on:click="deleteItem(item)">mdi-delete</v-icon>
					<!-- </template> -->
				</v-list-item>
			</template>
		</v-list-item-group>
	</v-list>
</template>

<script>
import { TodoApi } from '@/lib/todo-api';

export default {
	props: ['items'],
	methods: {
		checkBoxToggle(item) {
			item.done = !item.done;
		},

		deleteItem(item) {
			const idx = this.items.findIndex(
				itemElem => itemElem.itemId === item.itemId,
			);
			TodoApi.DELETE(item.itemId);
			// eslint-disable-next-line
      this.items.splice(idx, 1)
		},

		test() {
			console.log('test');
		},
	},
};
</script>

<style></style>
