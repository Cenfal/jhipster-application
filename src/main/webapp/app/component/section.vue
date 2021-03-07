<template>
  <div class="section-container row" :class="[{'buttons-wrapper': buttonsWrap}, {'container': container}, className]" :data-section="sectionId" :style="cssVars">
    <component-renderer
      v-for="field in getFields"
      v-bind:key="field.id"
      :class="`col-lg-${getColumns(field)} component-wrapper` "
      v-bind:sectionId="sectionId"
      :field="field"
      v-on:component-updated="updated"
      :isFromBuilder="isFromBuilder"
    />
  </div>
</template>

<script>
import componentRenderer from "@/components/component-renderer";

export default {
  data() {
    return {
      isSuccess: false
    }
  },
  components: {
    componentRenderer
  },
  props: {
    sectionId: {
      type: [Number, String],
      required: false
    },
    fields: {
      type: Array,
      required: true
    },
    isFromBuilder: {
      type: Boolean,
      required: false
    },
    grid: {
      type: Object,
      required: false,
      default: () => {
        return {
          columns: 12,
          marginTop: 0,
          marginRight: 0,
          marginBottom: 0,
          marginLeft: 0,
          paddingTop: 0,
          paddingRight: 0,
          paddingBottom: 0,
          paddingLeft: 0
        };
      }
    },
    buttonsWrap: {
      type: Boolean
    },
    container: Boolean,
    className: String
  },
  mounted() {
    this.$eventHub.$on('display-success', () => this.isSuccess = true)
  },
  beforeDestroy() {
    this.$eventHub.$off('display-success')
  },
  computed: {
    getFields() {
      const { isSuccess } = this;
      return this.fields.filter((field) => {
        if (field.properties && field.properties.onSuccess) {
          return field.properties.onSuccess === 'show' ? isSuccess :
            field.properties.onSuccess === 'hide' ? !isSuccess :
            true;
        } else {
          return true;
        }
      })
    }
  },
  methods: {
    getColumns(field) {
      return field.properties && field.properties.grid
        ? field.properties.grid.columns
        : 12;
    },
    updated(field, properties) {
      let updatedField = this._.find(this.fields, function(fld) {
        return fld.fieldId === field.fieldId;
      });
      // field.properties.text = properties.text;
      // updatedField.properties.text= "Hello"
      updatedField.properties = { ...updatedField.properties, ...properties };

      // console.log(
      //   "UPDATED:::",
      //   field,
      //   "properties",
      //   properties,
      //   "updatedField",
      //   updatedField
      // );
    }
  }
};
</script>

<style lang="scss">
.buttons-wrapper {
  justify-content: space-around;
  margin: auto;
  @media (max-width: 767px) {
    flex-direction: column-reverse;
  }
}
.mobile-only {
  display: none !important;
}
.desktop-none {
  display: none !important;
}
@media (max-width: 991px) {
  .desktop-only {
    display: none !important;
  }
  .desktop-none {
    display: initial !important;
  }
}
@media (max-width: 991px) {
  .mobile-none {
    display: none !important;
  }
  .mobile-only {
    display: grid !important;
  }
}
.container {
  margin-left: auto !important;
  margin-right: auto !important;
}
.button-section {
  justify-content: space-between;
  margin: 40px auto 0 auto;
  padding-bottom: 40px;
  max-width: 1500px;
  @media (max-width: 1199px) {
    max-width: 71%;
  }
  @media (max-width: 575px) {
    max-width: 100%;
    flex-direction: column !important;
  }
  @media (max-width: 425px) {
    margin: 35px auto 0 auto;
    padding-bottom: 30px;
  }
}
</style>
