import mixins from '../mixins';
const generateUID = mixins.generateUID.methods.generateUID;

export default {
  isEditMode: true,
  pageProperties: {},
  pageBuilder: {
    selectedField: {
      sectionId: '',
      fieldId: generateUID(),
    },
  },
  componentStatus: {},
  modal: {
    type: 'error',
    message: 'An error occured.',
    isActive: false,
  },
  customerToken: '',
  receiptToken: '',
  campaignId: '',
  formData: new FormData(),
  referenceNumber: '',
};
