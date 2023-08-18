// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  //apiUrl: 'http://165.232.184.105:9090',
  apiUrl: 'http://localhost:3000',
  // apiUrl: 'http://ec2-13-235-248-102.ap-south-1.compute.amazonaws.com:3000',
  // apiUrl: 'http://ec2-35-154-251-102.ap-south-1.compute.amazonaws.com:3000',
  flaskurl: "http://ec2-3-7-78-21.ap-south-1.compute.amazonaws.com:8000",
  digiurl: "http://ec2-3-111-96-142.ap-south-1.compute.amazonaws.com:3000",
  production: true
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
