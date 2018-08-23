package moneytap.com.task.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchedList {

    /**
     * batchcomplete : true
     * continue : {"gpsoffset":10,"continue":"gpsoffset||"}
     * query : {"redirects":[{"index":4,"from":"Sachin Tyagi","to":"Solhah Singaarr"},{"index":3,"from":"Sachin The Film","to":"Sachin: A Billion Dreams"}],"pages":[{"pageid":57570,"ns":0,"title":"Sachin Tendulkar","index":1,"thumbnail":{"source":"https://upload.wikimedia.org/wikipedia/commons/thumb/2/25/Sachin_Tendulkar_at_MRF_Promotion_Event.jpg/50px-Sachin_Tendulkar_at_MRF_Promotion_Event.jpg","width":50,"height":45},"terms":{"description":["Indian cricketer"]}},{"pageid":6957873,"ns":0,"title":"Sachin Pilot","index":7,"thumbnail":{"source":"https://upload.wikimedia.org/wikipedia/commons/thumb/b/bb/Sachin_Pilot_at_the_India_Economic_Summit_2010_cropped.jpg/39px-Sachin_Pilot_at_the_India_Economic_Summit_2010_cropped.jpg","width":39,"height":50},"terms":{"description":["Indian Politician, born 1977"]}},{"pageid":10730502,"ns":0,"title":"Sachin (actor)","index":6,"thumbnail":{"source":"https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/SachinPilgaonkar.jpg/33px-SachinPilgaonkar.jpg","width":33,"height":50},"terms":{"description":["Indian actor, director, producer, Writer, singer"]}},{"pageid":11017085,"ns":0,"title":"Sachin Bhowmick","index":9,"terms":{"description":["Film screenwriter and director"]}},{"pageid":12355232,"ns":0,"title":"Solhah Singaarr","index":4},{"pageid":20817418,"ns":0,"title":"Sachin Khedekar","index":10,"thumbnail":{"source":"https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/Sachin_Khedekar.jpg/50px-Sachin_Khedekar.jpg","width":50,"height":38},"terms":{"description":["Indian actor"]}},{"pageid":42182160,"ns":0,"title":"Sachin Bansal","index":8,"terms":{"description":["Indian Entrepreneur, founder and CEO of Flipkart.com"]}},{"pageid":42382589,"ns":0,"title":"Sachin! Tendulkar Alla","index":2,"terms":{"description":["2014 film by Mohan Shankar"]}},{"pageid":50125865,"ns":0,"title":"Sachin: A Billion Dreams","index":3,"terms":{"description":["2017 Indian biographical film"]}},{"pageid":53197293,"ns":0,"title":"Sachintha Peiris","index":5,"terms":{"description":["cricketer"]}}]}
     */

    private boolean batchcomplete;
    @SerializedName("continue")
    private ContinueBean continueX;
    private QueryBean query;

    public boolean isBatchcomplete() {
        return batchcomplete;
    }

    public void setBatchcomplete(boolean batchcomplete) {
        this.batchcomplete = batchcomplete;
    }

    public ContinueBean getContinueX() {
        return continueX;
    }

    public void setContinueX(ContinueBean continueX) {
        this.continueX = continueX;
    }

    public QueryBean getQuery() {
        return query;
    }

    public void setQuery(QueryBean query) {
        this.query = query;
    }

    public static class ContinueBean {
        /**
         * gpsoffset : 10
         * continue : gpsoffset||
         */

        private int gpsoffset;
        @SerializedName("continue")
        private String continueX;

        public int getGpsoffset() {
            return gpsoffset;
        }

        public void setGpsoffset(int gpsoffset) {
            this.gpsoffset = gpsoffset;
        }

        public String getContinueX() {
            return continueX;
        }

        public void setContinueX(String continueX) {
            this.continueX = continueX;
        }
    }

    public static class QueryBean {
        private List<RedirectsBean> redirects;
        private List<PagesBean> pages;

        public List<RedirectsBean> getRedirects() {
            return redirects;
        }

        public void setRedirects(List<RedirectsBean> redirects) {
            this.redirects = redirects;
        }

        public List<PagesBean> getPages() {
            return pages;
        }

        public void setPages(List<PagesBean> pages) {
            this.pages = pages;
        }

        public static class RedirectsBean {
            /**
             * index : 4
             * from : Sachin Tyagi
             * to : Solhah Singaarr
             */

            private int index;
            private String from;
            private String to;

            public int getIndex() {
                return index;
            }

            public void setIndex(int index) {
                this.index = index;
            }

            public String getFrom() {
                return from;
            }

            public void setFrom(String from) {
                this.from = from;
            }

            public String getTo() {
                return to;
            }

            public void setTo(String to) {
                this.to = to;
            }
        }

        public static class PagesBean implements Parcelable {
            /**
             * pageid : 57570
             * ns : 0
             * title : Sachin Tendulkar
             * index : 1
             * thumbnail : {"source":"https://upload.wikimedia.org/wikipedia/commons/thumb/2/25/Sachin_Tendulkar_at_MRF_Promotion_Event.jpg/50px-Sachin_Tendulkar_at_MRF_Promotion_Event.jpg","width":50,"height":45}
             * terms : {"description":["Indian cricketer"]}
             */

            private int pageid;
            private int ns;
            private String title;
            private int index;
            private ThumbnailBean thumbnail;
            private TermsBean terms;

            public int getPageid() {
                return pageid;
            }

            public void setPageid(int pageid) {
                this.pageid = pageid;
            }

            public int getNs() {
                return ns;
            }

            public void setNs(int ns) {
                this.ns = ns;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getIndex() {
                return index;
            }

            public void setIndex(int index) {
                this.index = index;
            }

            public ThumbnailBean getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(ThumbnailBean thumbnail) {
                this.thumbnail = thumbnail;
            }

            public TermsBean getTerms() {
                return terms;
            }

            public void setTerms(TermsBean terms) {
                this.terms = terms;
            }

            public static class ThumbnailBean implements Parcelable {
                /**
                 * source : https://upload.wikimedia.org/wikipedia/commons/thumb/2/25/Sachin_Tendulkar_at_MRF_Promotion_Event.jpg/50px-Sachin_Tendulkar_at_MRF_Promotion_Event.jpg
                 * width : 50
                 * height : 45
                 */

                private String source;
                private int width;
                private int height;

                public String getSource() {
                    return source;
                }

                public void setSource(String source) {
                    this.source = source;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.source);
                    dest.writeInt(this.width);
                    dest.writeInt(this.height);
                }

                public ThumbnailBean() {
                }

                protected ThumbnailBean(Parcel in) {
                    this.source = in.readString();
                    this.width = in.readInt();
                    this.height = in.readInt();
                }

                public static final Creator<ThumbnailBean> CREATOR = new Creator<ThumbnailBean>() {
                    @Override
                    public ThumbnailBean createFromParcel(Parcel source) {
                        return new ThumbnailBean(source);
                    }

                    @Override
                    public ThumbnailBean[] newArray(int size) {
                        return new ThumbnailBean[size];
                    }
                };
            }

            public static class TermsBean implements Parcelable {
                private List<String> description;

                public List<String> getDescription() {
                    return description;
                }

                public void setDescription(List<String> description) {
                    this.description = description;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeStringList(this.description);
                }

                public TermsBean() {
                }

                protected TermsBean(Parcel in) {
                    this.description = in.createStringArrayList();
                }

                public static final Creator<TermsBean> CREATOR = new Creator<TermsBean>() {
                    @Override
                    public TermsBean createFromParcel(Parcel source) {
                        return new TermsBean(source);
                    }

                    @Override
                    public TermsBean[] newArray(int size) {
                        return new TermsBean[size];
                    }
                };
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.pageid);
                dest.writeInt(this.ns);
                dest.writeString(this.title);
                dest.writeInt(this.index);
                dest.writeParcelable(this.thumbnail, flags);
                dest.writeParcelable(this.terms, flags);
            }

            public PagesBean() {
            }

            protected PagesBean(Parcel in) {
                this.pageid = in.readInt();
                this.ns = in.readInt();
                this.title = in.readString();
                this.index = in.readInt();
                this.thumbnail = in.readParcelable(ThumbnailBean.class.getClassLoader());
                this.terms = in.readParcelable(TermsBean.class.getClassLoader());
            }

            public static final Parcelable.Creator<PagesBean> CREATOR = new Parcelable.Creator<PagesBean>() {
                @Override
                public PagesBean createFromParcel(Parcel source) {
                    return new PagesBean(source);
                }

                @Override
                public PagesBean[] newArray(int size) {
                    return new PagesBean[size];
                }
            };
        }
    }
}
