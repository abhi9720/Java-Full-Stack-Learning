import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-blog-list',
  templateUrl: './blog-list.component.html',
  styleUrls: ['./blog-list.component.css']
})
export class BlogListComponent implements OnInit {
  mediumPosts: any[] = [];
  devToPosts: any[] = [];


  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.fetchMediumPosts();
    this.fetchDevToPosts();

    
  }

  async fetchMediumPosts() {
    const rssFeedUrl = 'https://api.rss2json.com/v1/api.json?rss_url=https://medium.com/feed/@abhi9720';
  
    this.http.get(rssFeedUrl)
      .subscribe((response: any) => {
        if (response.status === 'ok') {
          const items = response.items;
  
          items.forEach((item: { title: any; link: any; description: string; pubDate: string; author: any; content: any; categories: { length: number; map: (arg0: (category: any) => any) => never[]; }; }) => {
            
            const post = {
              title: item.title,
              link: item.link,
              cover_image: this.getFirstImage(item.description),
              categories: [], // Initialize an empty array to store categories
              createdAt: this.formatDate(item.pubDate),
              author: item.author,
              content: item.content
            };
          
            // Extract and populate categories
            if (item.categories && item.categories.length > 0) {
              post.categories = item.categories.map((category: string) => category.toLowerCase());
            }
          
            this.mediumPosts.push(post);
          });
          
        } else {
          console.error('Error fetching data:', response.message);
        }
      });

      
      
  }
  
  fetchDevToPosts() {
    this.http.get<any[]>('https://dev.to/api/articles/latest?username=abhi9720')
      .subscribe(posts => {
        this.devToPosts = posts;
      }, error => {
        console.error('Error fetching data:', error);
      });
      console.log(this.devToPosts.length);

  }


  

  
 

  truncateText(text: string, length: number = 150): string {
    return text.length > length ? text.substring(0, length) + '...' : text;
  }

  formatDate(dateString: string): string {
    const date = new Date(dateString);
    return date.toLocaleDateString('en-US', { year: 'numeric', month: 'long', day: 'numeric' });
  }

  getFirstImage(content: string): string {
    const regex = /<img.*?src="(.*?)"/;
    const match = regex.exec(content);    
    return match ? match[1] : '';
  }
}
