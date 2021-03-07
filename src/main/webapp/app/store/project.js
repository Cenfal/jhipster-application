import mixins from '../mixins';

const generateUID = mixins.generateUID.methods.generateUID;

export default {
  isEditMode: true,
  pageProperties: {
    testing: true,
    pages: [
      {
        // Home Page
        pageName: 'startseite',
        locale: 'de',
        pageId: 1,
        sections: [
          {
            // For Desktop
            sectionId: 'orbg8s',
            class: 'desktop-only',
            grid: {
              columns: 12,
              alignItems: 'center',
              justifyContent: 'center',
              position: 'relative',
            },
            fields: [
              {
                // Refresh Token
                fieldType: 'refresh-token',
                fieldId: 'ij3433',
              },
              {
                // No Logo
                fieldId: 'n0l0g0',
                fieldType: 'noLogo',
                properties: {
                  // eventName: "PageView",
                  // fbEvent: "PageView"
                },
              },
              {
                // DataLayer
                fieldId: 'dte-003',
                fieldType: 'dataLayer-event',
                properties: {
                  eventName: 'PageView',
                  fbEvent: 'PageView',
                },
              },
              {
                // Key Visual
                fieldType: 'key-visual',
                fieldId: '296eg1',
                properties: {
                  grid: {
                    columns: '11',
                  },
                  styles: {
                    padding: '0 0 0 0',
                  },
                  noContainer: true,
                  desktopImageUrl: 'assets/banner/banner03.jpg',
                  tabletImageUrl: 'assets/banner/banner03.jpg',
                  mobileImageUrl: 'assets/banner/banner01.jpg',
                },
              },
              {
                // Participation
                fieldType: 'empty-bucket',
                properties: {
                  grid: {
                    columns: 12,
                    marginTop: '40px',
                  },
                  styles: {
                    'justify-content': 'center ;',
                  },
                  fields: [
                    {
                      // Heading
                      fieldType: 'participation',
                      fieldId: '6mo8qp',
                      properties: {
                        text: 'Kontigentstand prüfen:',
                        grid: {
                          alignItems: 'center',
                          justifyContent: 'center',
                          columns: '7',
                        },
                        classes: ['heading'],
                      },
                    },
                  ],
                },
              },
              {
                // Steps Card
                fieldId: 'fu0923r',
                fieldType: 'empty-bucket',
                properties: {
                  grid: {
                    columns: '10',
                    marginTop: '40px',
                  },
                  fields: [
                    {
                      fieldType: 'step-card',
                      fieldId: '05m65j',
                      properties: {
                        stepNumber: '1',
                        stepStyles: {
                          background: 'rgb(230, 245, 235)',
                          color: '#009639',
                          fontFamily: 'Jost',
                        },
                        imageSource: '/assets/step_card_1.svg',
                        grid: {
                          columns: 4,
                        },
                        fields: [
                          {
                            fieldType: 'paragraph',
                            fieldId: 'psv9ff',
                            properties: {
                              text: 'Produkt auswählen',
                              classes: ['stepCard'],
                            },
                          },
                        ],
                      },
                    },
                    {
                      fieldType: 'step-card',
                      fieldId: 'ec7jsf',
                      properties: {
                        stepNumber: '2',
                        stepStyles: {
                          background: 'rgb(230, 245, 235)',
                          color: '#009639',
                          fontFamily: 'Jost',
                        },
                        imageSource: 'assets/step_card_2.svg',
                        grid: {
                          columns: 4,
                        },
                        fields: [
                          {
                            fieldType: 'paragraph',
                            fieldId: '6sjsfj',
                            properties: {
                              text: 'Kaufbeleg hochladen',
                              classes: ['stepCard'],
                            },
                          },
                        ],
                      },
                    },
                    {
                      fieldType: 'step-card',
                      fieldId: '16pk71',
                      properties: {
                        stepNumber: '3',
                        stepStyles: {
                          background: 'rgb(230, 245, 235)',
                          color: '#009639',
                          fontFamily: 'Jost',
                        },
                        imageSource: '/assets/step_card_3.svg',
                        grid: {
                          columns: 4,
                        },
                        fields: [
                          {
                            fieldType: 'paragraph',
                            fieldId: 'qo8x4a',
                            properties: {
                              text: 'Teilnahmeformular ausfüllen',
                              classes: ['stepCard'],
                            },
                          },
                        ],
                      },
                    },
                  ],
                },
              },
              {
                // Button
                fieldType: 'empty-bucket',
                properties: {
                  grid: {
                    columns: 12,
                    marginTop: '40px',
                  },
                  styles: {
                    'justify-content': 'center ;',
                  },
                  fields: [
                    {
                      fieldType: 'button-component',
                      fieldId: 'x1p9hx',
                      properties: {
                        grid: {
                          columns: '3',
                        },
                        text: 'RÜCKERSTATTUNG STARTEN',
                        addClass: 'homePage',
                        disabled: false,
                        action: [
                          {
                            fn: 'goToPage',
                            params: 'produkt-auswählen',
                          },
                        ],
                      },
                    },
                  ],
                },
              },
              {
                // 5 points
                fieldType: 'empty-bucket',
                properties: {
                  grid: {
                    columns: 12,
                    marginTop: '60px',
                  },
                  styles: {
                    'justify-content': 'center ;',
                  },
                  fields: [
                    {
                      // Five Points
                      fieldType: 'fivePoints',
                      fieldId: '6mo8qp',
                      properties: {
                        grid: {
                          alignItems: 'center',
                          justifyContent: 'center',
                          columns: '11',
                        },
                        points: [
                          'Aktionszeitraum: <br/> 01.04.2021- 30.09.2021',
                          'Die Teilnahme an der Lenor Gratis Testen Aktion ist pro Person auf ein 1 Mal beschränkt.',
                          'Maximal 3 Lenor Produkte werden rückerstattet. Hierbei muss jedes der drei Produkte aus unterschiedlichen Warengruppen (Waschmittel, Weichspüler, Wäscheparfüm) sein.',
                          `Nur gültig für teilnehmende Lenor Produkte mit einem gelben "Gratis-Testen" Hinweis.`,
                          'Die Aktion ist insgesamt auf maximal 200.000 Einlösungen innerhalb des Aktionszeitraums limitiert.',
                        ],
                      },
                    },
                  ],
                },
              },
            ],
          },
          {
            // For Mobile
            sectionId: 'orbg8s',
            class: 'mobile-only',
            grid: {
              columns: 12,
              alignItems: 'center',
              justifyContent: 'center',
              position: 'relative',
            },
            fields: [
              {
                // Refresh Token
                fieldType: 'refresh-token',
                fieldId: 'ij3433',
              },
              {
                // DataLayer
                fieldId: 'dte-003',
                fieldType: 'dataLayer-event',
                properties: {
                  eventName: 'PageView',
                  fbEvent: 'PageView',
                },
              },
              {
                // Key Visual
                fieldType: 'key-visual',
                fieldId: '296eg1',
                properties: {
                  grid: {
                    columns: '12',
                  },
                  styles: {
                    padding: '0 0 0 0',
                  },
                  noContainer: true,
                  desktopImageUrl: 'assets/banner/banner03.jpg',
                  tabletImageUrl: 'assets/banner/banner03.jpg',
                  mobileImageUrl: 'assets/banner/banner01.jpg',
                },
              },
              {
                // Participation and 5 steps and Steps
                fieldType: 'empty-bucket',
                properties: {
                  grid: {
                    columns: 12,
                    marginTop: '40px',
                  },
                  styles: {
                    'justify-content': 'center ;',
                    margin: '0px;',
                  },
                  fields: [
                    {
                      // Participation and 5 steps
                      fieldType: 'empty-bucket',
                      properties: {
                        grid: {
                          marginTop: '40px',
                        },
                        class: 'col-6 p-0 m-0',
                        styles: {
                          'justify-content': 'center ;',
                          margin: '0px;',
                        },
                        fields: [
                          {
                            // Participation
                            fieldType: 'empty-bucket',
                            properties: {
                              grid: {
                                columns: 12,
                              },
                              styles: {
                                margin: '20px auto ;',
                                'justify-content': 'center ;',
                              },
                              fields: [
                                {
                                  // Participation Text
                                  fieldType: 'participation',
                                  fieldId: '6mo8qp',
                                  properties: {
                                    text: 'Kontigentstand prüfen:',
                                    mobile: true,
                                    grid: {
                                      alignItems: 'center',
                                      justifyContent: 'center',
                                      columns: '7',
                                    },
                                    classes: ['heading'],
                                  },
                                },
                              ],
                            },
                          },
                          {
                            // 5 points
                            fieldType: 'empty-bucket',
                            properties: {
                              grid: {
                                columns: 12,
                              },
                              styles: {
                                'justify-content': 'center;',
                                padding: '0px',
                              },
                              fields: [
                                {
                                  // Five Points
                                  fieldType: 'fivePoints',
                                  fieldId: '6mo8qp',
                                  properties: {
                                    grid: {
                                      alignItems: 'center',
                                      justifyContent: 'center',
                                      columns: '12',
                                    },
                                    mobile: true,
                                    points: [
                                      'Aktionszeitraum: <br/><b>01.04.2021- 30.09.2021</b>',
                                      'Die Teilnahme an der Lenor Gratis Testen Aktion ist pro Person auf ein 1 Mal beschränkt.',
                                      'Maximal 3 Lenor Produkte werden rückerstattet. Hierbei muss jedes der drei Produkte aus unterschiedlichen Warengruppen (Waschmittel, Weichspüler, Wäscheparfüm) sein.',
                                      `Nur gültig für teilnehmende Lenor Produkte mit einem gelben "Gratis-Testen" Hinweis.`,
                                      'Die Aktion ist insgesamt auf maximal 200.000 Einlösungen innerhalb des Aktionszeitraums limitiert.',
                                    ],
                                  },
                                },
                              ],
                            },
                          },
                        ],
                      },
                    },
                    {
                      // Steps Card
                      fieldId: 'fu0923r',
                      fieldType: 'empty-bucket',
                      properties: {
                        grid: {
                          marginTop: '40px',
                        },
                        class: 'col-6 p-0 m-0',
                        fields: [
                          {
                            fieldType: 'step-card',
                            fieldId: '05m65j',
                            properties: {
                              stepNumber: '1',
                              stepStyles: {
                                background: 'rgb(230, 245, 235)',
                                color: '#009639',
                                fontFamily: 'Jost',
                              },
                              imageSource: '/assets/step_card_1.svg',
                              grid: {
                                columns: 4,
                              },
                              fields: [
                                {
                                  fieldType: 'paragraph',
                                  fieldId: 'psv9ff',
                                  properties: {
                                    text: 'Produkt auswählen',
                                    classes: ['stepCard'],
                                  },
                                },
                              ],
                            },
                          },
                          {
                            fieldType: 'step-card',
                            fieldId: 'ec7jsf',
                            properties: {
                              stepNumber: '2',
                              stepStyles: {
                                background: 'rgb(230, 245, 235)',
                                color: '#009639',
                                fontFamily: 'Jost',
                              },
                              imageSource: 'assets/step_card_2.svg',
                              grid: {
                                columns: 4,
                              },
                              fields: [
                                {
                                  fieldType: 'paragraph',
                                  fieldId: '6sjsfj',
                                  properties: {
                                    text: 'Kaufbeleg hochladen',
                                    classes: ['stepCard'],
                                  },
                                },
                              ],
                            },
                          },
                          {
                            fieldType: 'step-card',
                            fieldId: '16pk71',
                            properties: {
                              stepNumber: '3',
                              stepStyles: {
                                background: 'rgb(230, 245, 235)',
                                color: '#009639',
                                fontFamily: 'Jost',
                              },
                              imageSource: '/assets/step_card_3.svg',
                              grid: {
                                columns: 4,
                              },
                              fields: [
                                {
                                  fieldType: 'paragraph',
                                  fieldId: 'qo8x4a',
                                  properties: {
                                    text: 'Teilnahmeformular ausfüllen',
                                    classes: ['stepCard'],
                                  },
                                },
                              ],
                            },
                          },
                        ],
                      },
                    },
                  ],
                },
              },
              {
                // Button
                fieldType: 'empty-bucket',
                properties: {
                  grid: {
                    columns: 12,
                  },
                  styles: {
                    'justify-content': 'center ;',
                    margin: '20px auto;',
                  },
                  fields: [
                    {
                      fieldType: 'button-component',
                      fieldId: 'x1p9hx',
                      properties: {
                        grid: {
                          columns: '12',
                        },
                        text: 'RÜCKERSTATTUNG STARTEN',
                        addClass: 'homePage',
                        disabled: false,
                        action: [
                          {
                            fn: 'goToPage',
                            params: 'produkt-auswählen',
                          },
                        ],
                      },
                    },
                  ],
                },
              },
            ],
          },
        ],
      },
    ],
    header: {
      fieldType: 'navbar',
      fieldId: 'cbx34t',
      properties: {
        dataLayer: {
          GTM: {
            SiteLocale: 'de-de',
            SiteTouchpoint: '',
            SiteCountry: 'DE',
            SiteBrand: '',
            SiteLanguage: 'DE',
            SitePlatform: '',
            SiteEnvironment: 'Prod',
            SiteHost: 'CTL',
            SiteTechnicalAgency: 'Justsnap',
            SiteStatus: 'Live',
            GoogleAnalyticsLocal: 'UA-10412122-22',
            GoogleAnalyticsBrand: '',
            GoogleAnalyticsSiteSpeedSampleRate: 'high',
            GoogleAnalyticsAllowLinker: 'true',
            GoogleAnalyticsLinkerDomains: '',
            GoogleAnalyticsEnableOptimizely: 'false',
            GoogleAnalyticsConsentRequired: 'true',
            GoogleAnalyticsOptimizeContainerID: '',
            GoogleAnalyticsReportingView: '',
            GoogleReCaptcha: 'true',
            SiteLocalContainer: '',
            FacebookRemarketingID: '590487274764573',
            ConsentOverlay: 'OneTrust',
            ConsentOverlayID: 'htmllang/40411c36-6cb3-494b-98fe-162ed5d8e13b-test',
            SitePrivacyProtection: 'GDPR',
            CookieID: 'htmllang/84e13b23-a3e0-4b32-a2d3-48953967aa29-test',
          },
          GTM_CODE: 'GTM-PW4NS3V',
        },
        neustar: {
          url: 'https://aa.agkn.com/adscores/g.js?sid=9212300878',
        },
        // "facebookPixel": {
        //   init: "590487274764573"
        // },
        backgroundColor: '#fff',
        title: '',
        color: '#FFE082',
        grid: {
          columns: 12,
          'max-width': 'none',
        },
        logo: {
          imageSource: '/assets/header-logo.png',
          width: 100,
          alternateText: 'Brand Logo',
          paddingTop: '30px',
        },
        favicon: {
          imageSource: 'https://azcdn.pioneer.pgsitecore.com/en-gb/-/media/Downy_UK/Images/Common/favicon-32x32.jpg?v=1-201708041111',
        },
        fields: [
          {
            fieldId: 'ikz8vc',
            fieldType: 'anchor',
            properties: {
              text: 'Teilnahmebedingungen',
              url: '/teilnahmebedingungen',
              target: '_blank',
              cssClass: 'nav-link headingLink',
            },
          },
          {
            fieldId: 'slnmdk',
            fieldType: 'anchor',
            properties: {
              text: 'FAQ',
              url: '/faq',
              target: '_blank',
              cssClass: 'nav-link headingLink',
            },
          },
        ],
        showLine: false,
        meta: [
          { name: 'title', content: 'Lenor Perfect Match' },
          {
            name: 'description',
            content: `Lenor Testen Aktion - Lenor Trocknertücher für Frische, Weichheit und weniger Falten nach dem Trocknervorgang. Mehr erfahren. `,
          },
          {
            name: 'keywords',
            content: `Lenor Dosierkappe für Flüssigwaschmittel, ‎Lenor Produktpalette , ‎Lenor Tipps & Tricks , ‎Weichspüler`,
          },
        ],
      },
    },
    footer: {
      fieldType: 'footer-component',
      fieldId: '4tfbzz',
    },
    campaignId: 'lenor-pm',
    url: 'lenor-pods-testen.de',
    name: 'lenor-pm',
  },
  pageBuilder: {
    selectedField: {
      sectionId: '',
      fieldId: generateUID(),
    },
  },
  componentStatus: {},
  modal: {
    type: 'error',
    message: 'Ein Fehler ist aufgetreten',
    isActive: false,
  },
  customerToken: '',
  receiptToken: '',
  receiptImage: '',
  campaignId: 'lenor-pm',
  formData: new FormData(),
  campaignEndDate: '2021-10-16',
  scannerActive: false,
  file: {},
  barcode: {
    code: '',
    format: '',
  },
  productCode: '',
  searchStatus: null,
  imgList: [],
  errorMessage:
    'Der Barcode ist ungültig. Bitte versuchen Sie erneut, den Barcode einzugeben oder zu scannen. Glauben Sie, dass es sich um einen Fehler handelt?',
  errorImageList: ['', ''],
  errorActivate: false,
  scannedProducts: [],
  types: [
    'ean_reader',
    'code_128_reader',
    'code_39_reader',
    'ean_8_reader',
    'upc_reader',
    'upc_e_reader',
    'i2of5_reader',
    '2of5_reader',
    'code_93_reader',
    'codabar_reader',
  ],
  pageToBack: '',
  receiptBarcode: '',
  isReceiptImageChanged: false,
  convertedImagePointsData: {},
  firstTimeUploading: true,
  removeLogo: false,
};
