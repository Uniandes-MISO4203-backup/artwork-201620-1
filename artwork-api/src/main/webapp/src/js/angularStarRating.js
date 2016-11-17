/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    ng.module('angular-star-rating', [])
    .directive('angularStarRating', angularStarRating);

    function angularStarRating() {
        var directive = {
            restrict: 'EA',
            scope: {
                'value': '=value',
                'max': '=max',
                'hover': '=hover',
                'isReadonly': '=isReadonly'
            },
            link: linkFunc,
            template: '<span ng-class="{isReadonly: isReadonly}">' +
                    '<i ng-class="renderObj" ' +
                    'ng-repeat="renderObj in renderAry" ' +
                    'ng-click="setValue($index)" ' +
                    'ng-mouseenter="changeValue($index, changeOnHover )" >' +
                    '</i>' +
                    '</span>',
            replace: true
        };
        return directive;
    }

    function linkFunc(scope, element, attrs, ctrl) {
        if (scope.max === undefined) {
            scope.max = 5; // default
        }
        console.log(scope.test);
        function renderValue() {
            scope.renderAry = [];
            for (var i = 0; i < scope.max; i++) {
                if (i < scope.value) {
                    scope.renderAry.push({
                        'fa fa-star fa-2x': true
                    });
                } else {
                    scope.renderAry.push({
                        'fa fa-star-o fa-2x': true
                    });
                }
            }
        }

        scope.setValue = function (index) {
            if (!scope.isReadonly && scope.isReadonly !== undefined) {
                scope.value = index + 1;
            }
        };

        scope.changeValue = function (index) {
            if (scope.hover) {
                scope.setValue(index);
            } else {
                // !scope.changeOnhover && scope.changeOnhover != undefined
            }
        };

        scope.$watch('value', function (newValue, oldValue) {
            // console.log('oldValue -> newValue', oldValue, newValue);
            if (newValue) {
                renderValue();
            }
        });

        scope.$watch('max', function (newValue, oldValue) {
            // console.log('oldValue -> newValue', oldValue, newValue);
            if (newValue) {
                renderValue();
            }
        });
    }
})(window.angular);

