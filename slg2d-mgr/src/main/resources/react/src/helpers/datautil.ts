import moment from 'moment';

// 生成特定格式时间字符串
export function calDateStr(date: moment.Moment): string {
    return date.format('YYYY-MM-DD HH:mm:ss');
}