<template>
  <form-wizard
    ref="pageSlider"
    :hideButtons="!pageProperties.testing"
    @on-change="handlePageChange"
    :pageFooter="footer"
    :pageHeader="header"
  >
    <modal />
    <tab-content
      v-for="page in pageProperties.pages"
      :key="page.pageId"
      :title="page.pageName"
      v-bind="page"
    >
      <div class="container-fluid" :style="`max-width: ${page.containerWidth}px; background: #FBFBFB 0% 0% no-repeat padding-box;`">
        <ValidationObserver ref="form">
          <pageSection v-for="section in page.sections" :key="section.id" v-bind="section" />
        </ValidationObserver>
      </div>
    </tab-content>
  </form-wizard>
</template>

<script>
import pageSection from "@/components/section";
import { FormWizard, TabContent } from "@/components/renderers/FormWizard";
import debounce from "lodash/debounce";
import modal from "./modal";
import { localize } from "vee-validate";
// import ar from 'vee-validate/dist/locale/ar.json';

export default {
  data: () => {
    return { footer: {}, header: {} };
  },
  components: {
    pageSection,
    FormWizard,
    TabContent,
    modal
  },
  props: {
    pageId: {
      type: Number
    },
    containerWidth: {
      type: Number,
      required: false,
      default: 1200
    }
  },
  computed: {
    pageProperties() {
      return this.$store.getters.getPageProperties;
    },
    pages() {
      return this.$store.getters.getPages;
    },
    editMe: {
      get() {
        var json = this.$store.getters.getPageProperties;
        return JSON.stringify(json, null, 2);
      },
      set(value) {
        this.updateProperties(value);
      }
    }
  },
  mounted(){
      this.footer = this.pageProperties.footer;
      this.header = this.pageProperties.header;
  },
  methods: {
    updateProperties: debounce(function(value) {
      // Install and Activate the Arabic locale.

      this.$store.dispatch("setPageProperties", JSON.parse(value));
    }, 2000),
    handlePageChange: function(prevIndex, nextIndex) {
      const pageStatus = {
        current: this.pageProperties.pages[nextIndex],
        currentIdx: nextIndex,
        prev: this.pageProperties.pages[prevIndex],
        prevIdx: prevIndex
      };
      this.$store.commit("setStep", {
        pageStatus
      });
      // console.log("Page Change,", nextIndex, pageStatus, "dependencies");
      this.scrollTo("body");
      if (
        this.pageProperties.pages[nextIndex] &&
        this.pageProperties.pages[nextIndex].locale
      ) {
        // console.log("PAGE CHANGE", this.locale);
        this.locale = this.pageProperties.pages[nextIndex].locale;
      }
      this.footer = this.pageProperties.footer;
      this.header = this.pageProperties.header;
    }
  }
};
</script>

