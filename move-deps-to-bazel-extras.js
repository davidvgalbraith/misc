var fs = require('fs');
var _ = require('underscore');
var lines = fs.readFileSync('/Users/davidgalbraith/shift/trimmed.txt').toString().split('\n');
main();

// Get the output for each failed test. If it failed because it couldn't
// find a file, write that file into its BUILD.gazel-extras
function main() {
  var alreadyDone = {};
  for (var i = 0; i < lines.length; i++) {
    if (!lineIsTestOutput(lines[i])) {
      continue;
    }
    var testDir = lines[i].substring(lines[i].indexOf('backend'), lines[i].indexOf(':'));
    for (var j = i + 1; lines[j] !== undefined && !lines[j].match(/Test output/); j++) {
      if (!lines[j].match(/no such file/)) {
        continue;
      }
      var bazelFile = testDir + '/BUILD.gazel-extras';
      var bazelLines = fs.readFileSync(bazelFile).toString().split('\n');
      var deps = bazelLines.slice(1, bazelLines.indexOf(']'));
      var dep = getDep(lines[j]);
      if (alreadyDone[bazelFile] && alreadyDone[bazelFile][dep]) {
        break;
      }
      alreadyDone[bazelFile] = alreadyDone[bazelFile] || {};
      alreadyDone[bazelFile][dep] = true;
      deps.push('    "' + dep + '",');
      writeBazelFile(bazelFile, deps, bazelLines);
    }
  }
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
  if (dep.match(/go_sources/)) {
    return String.fromCharCode(1);
  }
  return dep;
}

// sample line: ==================== Test output for //backend/src/shift/web/routes/cargigi:TestCompSummaries:
// due to concurrency it can get smeared into a previous line which breaks everything
// this looks like FAIL
function lineIsTestOutput(line) {
  return line.match(/Test output/) && !line.match(/FAIL/);
}
// sample line: panic: open /private/var/tmp/_bazel_davidgalbraith/7971f50d2dba8ce4f9ae3221ff26acd8/execroot/shift/bazel-out/local-fastbuild/bin/backend/src/shift/web/routes/clientapi/consumer/TestStandard50ShiftWithOptions.runfiles/__main__/backend/src/shift/model/pricing/retail-guidelines-shift.json: no such file or directory [recovered]
// We extract the name of the missing file, without all the other directories.
function getDep(line) {
  var dep = '/' + line.split('__main__')[1].split(':')[0].replace(/_|\./g, '-');
  var lastSlash = dep.lastIndexOf('/');
  return dep.substring(0, lastSlash) + ':' + dep.substring(lastSlash+1);
}
