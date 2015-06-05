/**
 * Created by yagamai on 15-6-4.
 */

var LocalNotificationPlugin = {
    notify: function(title, content) {
        cordova.exec(
            null,
            null,
            'LocalNotificationPlugin',
            'notify', [{
                "title": title,
                "content": content
            }]
        );
    }
};

module.exports = LocalNotificationPlugin;
