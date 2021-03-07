<template>
  <component
    v-bind:is="field.fieldType"
    v-bind="field.properties"
    :data-field-id="field.fieldId"
    :fieldId="field.fieldId"
    :data-field-type="field.fieldType"
    v-on:component-updated="updated"
    @click.native="componentClicked"
    :style="`${fieldCssVars(field)}`"
    :disabled="!canProceed"
  />
</template>

<script>
export default {
  props: {
    field: {
      type: [Array, Object],
      required: true,
      default:()=>[]
    },
    sectionId: {
      type: [Number, String],
      required: false
    },
    isFromBuilder: {
      type: Boolean,
      required: false
    }
  },
  data() {
    return {
      canProceed: true
    };
  },
  mounted() {
    /* global $ */
    $('[data-toggle="tooltip"]').tooltip();

    if (this.field && this.field.properties && this.field.properties.shouldCheckCrop) {
      this.$eventHub.$on('canProceedNext', this.setDisabled)
    }
  },
  methods: {
    setDisabled(val) {
      this.$eventHub.$emit('image-is-correct', false);
      this.canProceed = val;
    },
    fieldCssVars(field) {
      if (field.properties && field.properties.grid) {
        const fieldStyles = field.properties.grid;
        return `
              margin-top:${fieldStyles.marginTop};
              margin-bottom:${fieldStyles.marginBottom};
              margin-left:${fieldStyles.marginLeft};
              margin-right:${fieldStyles.marginRight};
              padding-top:${fieldStyles.paddingTop};
              padding-left:${fieldStyles.paddingLeft};
              padding-right:${fieldStyles.paddingRight};
              padding-bottom:${fieldStyles.paddingBottom};
      `;
      }
    },
    componentClicked() {
      if (!this.isFromBuilder) {
        return;
      }

      // console.log(
      //   "CLICKED",
      //   ">>>>>>sectionId",
      //   this.sectionId,
      //   "from FormBuilder?",
      //   this.isFromBuilder,
      //   "fieldId",
      //   this.field.fieldId
      // );
      const selected = {
        sectionId: this.sectionId,
        fieldId: this.field.fieldId
      };
      this.$store.commit("pageBuilderSelectField", selected);
    },
    updated(properties) {
      this.$emit("component-updated", this.field, properties);
    }
  }
};
</script>
