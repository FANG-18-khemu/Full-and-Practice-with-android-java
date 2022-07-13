import React, { Component } from "react";
import NewsItem from "./NewsItem";
import Loader from "./Loader";
import PropTypes from "prop-types";
import InfiniteScroll from "react-infinite-scroll-component";

export class News extends Component {
  static defaultProps = {
    country: "in",
    pageSize: 9,
    category: "general",
  };

  static propTypes = {
    country: PropTypes.string,
    pageSize: PropTypes.number,
    category: PropTypes.string,
  };

  capitalfun = (string) => {
    return string.charAt(0).toUpperCase() + string.slice(1);
  };

  constructor(props) {
    super(props);

    this.state = {
      articles: [],
      loading: true,
      page: 1,
      totalResults: 0,
    };
    document.title = `NewsLizard - ${this.capitalfun(this.props.category)}`;
  }
  async helperFunction() {
    const url = `https://newsapi.org/v2/top-headlines?country=in&category=${this.props.category}&apiKey=${this.props.apiKey}&page=${this.state.page}&pageSize=${this.props.pageSize}`;
    let data = await fetch(url).then((res) => res.json());
    this.setState({
      articles: data.articles,
      totalResults: data.totalResults,
      loading: false,
    });
  }
  async componentDidMount() {
    this.helperFunction();
  }

  fetchMoreData = async () => {

    const url = `https://newsapi.org/v2/top-headlines?country=in&category=${this.props.category}&apiKey=${this.props.apiKey}&page=${this.state.page+1}&pageSize=${this.props.pageSize}`;

    let data = await fetch(url).then((res) => res.json());
    this.setState({
      articles: this.state.articles.concat(data.articles),
      totalResults: data.totalResults,
      page:this.state.page+1
    });
  }

  render() {
    return (
      <>
        <div className="mt-4 pt-4" style={{ backgroundColor: "#e9ecef" }}>
        
            <h2 className="text-danger text-center py-4">
              NewsLizard - Top headlines from{" "}
              {this.capitalfun(this.props.category)} category
            </h2>
            {this.state.loading && <Loader />}
            <InfiniteScroll
              dataLength={this.state.articles.length}
              next={this.fetchMoreData}
              hasMore={this.state.articles.length !== this.state.totalResults}
              loader={<Loader />}
            >
              <div className="container">
                <div className="row my-2">
                  {this.state.articles.map((element) => {
                    return (
                      <div className="col-md-4" key={element.url}>
                        <NewsItem
                          titile={
                            element.title !== null
                              ? element.title.slice(0, 40)
                              : ""
                          }
                          description={
                            element.description !== null
                              ? element.description.slice(0, 80)
                              : ""
                          }
                          imageUrl={
                            element.urlToImage !== null
                              ? element.urlToImage
                              : "https://a3.espncdn.com/combiner/i?img=%2Fphoto%2F2021%2F1026%2Fr928931_1296x729_16%2D9.jpg"
                          }
                          url={element.url !== null ? element.url : ""}
                          author={element.author}
                          date={element.publishedAt}
                          source={element.source.name}
                        />
                      </div>
                    );
                  })}
                </div>
              </div>
            </InfiniteScroll>

        </div>
      </>
    );
  }
}

export default News;
