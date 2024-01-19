import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'truncate'
})
export class TruncatePipe implements PipeTransform {
  transform(value: string): string {
    const words = value.split(' ');

    if (words.length > 25) {
      return words.slice(0, 25).join(' ') + '...';
    }

    return value;
  }
}
