var fs = require('fs');
var _ = require('underscore');
var files = fs.readFileSync('./files.txt').toString().split('\n');

var badDeps = {
  '"//backend/src/shift/model/pricing:average-mmr-adjustment-json",': true,
  '"//backend/src/shift/model/pricing:local-adjustments-json",': true,
  '"//backend/src/shift/model/mechanics:mechanics-json",': true,
  '"//backend/src/shift/model/rbook:option-impact-scores-csv",': true,
  '"//backend/src/shift/model/region:route-descriptions-json",': true,
  '"//backend/src/shift/model/shiftsegment:analytics-json",': true,
  '"//backend/src/shift/model/zipcode:zipcodes-json",': true,
  '"//backend/src/shift/model/pricing/pricingrules:premium-trims-csv",': true,
  '"//backend/src/shift/model/pricing:limits-json",': true,
  '"//backend/src/shift/model/pricing:performance-adjustments-json",': true,
  '"//backend/src/shift/model/pricing:retail-guidelines-json",': true,
  '"//backend/src/shift/model/regression:coefficients-json",': true,
  '"//backend/src/shift/model/warranty:manufacturer-warranties-json",': true,
  '"//backend/src/shift/model/enthusiasts:enthusiasts-test-json",': true
};

for (var i = 0; i < files.length; i++) {
  if (!files[i]) {
    continue;
  }
  var bazelLines = fs.readFileSync(files[i]).toString().split('\n');
  var deps = bazelLines.slice(1, bazelLines.indexOf(']')).filter(function(dep) {
    return !badDeps[dep.trim()];
  });
  writeBazelFile(files[i], deps, bazelLines);
}

function writeBazelFile(bazelFile, deps, bazelLines) {
  deps = _.sortBy(deps, sortDepsByName);
  deps.unshift('test_data = [');
  deps.push(']');
  for (var k = bazelLines.indexOf(']') + 1; k < bazelLines.length; k++) {
    deps.push(bazelLines[k]);
  }
  fs.writeFileSync(bazelFile, deps.join('\n'));
}

function sortDepsByName(dep) {
  if (dep.match(/\:/)) {
    return String.fromCharCode(1) + dep[1];
  }
  return dep;
}
