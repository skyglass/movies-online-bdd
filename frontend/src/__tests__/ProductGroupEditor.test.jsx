import React from 'react';
import ProductGroupEditor from '../ProductGroupEditor';
import {getByAltText, getByText, render, waitFor, within}
  from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import searchService from '../searchService';
import {when} from 'jest-when';

jest.mock('../searchService');

describe('ProductGroupEditor', () => {
  describe('initial render', () => {
    describe('when ProductGroup is undefined', () => {
      it('renders without error', () => {
        const wrapper = render(<ProductGroupEditor productGroup={undefined} />);
        expect(wrapper.baseElement).toBeInTheDocument();
      });
    });
  });

  describe('displaying albums', () => {
    describe('when ProductGroup has no products', () => {
      it('renders no albums', () => {
        const productGroup = {
          products: [],
          id: 1,
        };
        const wrapper = render(
            <ProductGroupEditor productGroup={productGroup} />,
        );
        expect(wrapper.queryAllByTestId('album')).toHaveLength(0);
      });
    });

    describe('when ProductGroup has one product', () => {
      it('renders the product', () => {
        const expectedArtist = {name: 'Bobby Joel'};
        const expectedAlbum = {
          artistName: {name: 'Bobby Joel'}.name,
          albumTitle: 'Subway entrance thing',
          albumAltText: 'Bobby Joel - Subway entrance thing',
          albumImage: 'http://example.com/image.png',
        };

        const productGroup = {
          products: [
            {
              artists: [expectedArtist],
              albumTitle: expectedAlbum.albumTitle,
              imageUrl: 'http://example.com/image.png',
            },
          ],
          id: 1,
        };

        const wrapper = render(
            <ProductGroupEditor productGroup={productGroup}/>,
        );
        const albums = wrapper.queryAllByTestId('album');

        expect(albums).toHaveLength(1);
        const first = albums[0];

        expectAlbum(first, expectedAlbum);
      });
    });

    describe('when ProductGroup has a few products', () => {
      it('renders each', () => {
        const expectedArtist = {name: 'Bobby Joel'};
        const expectedAlbumTitle = 'Subway entrance thing';
        const productGroup = {
          products: [
            {artists: [expectedArtist], albumTitle: expectedAlbumTitle},
            {artists: [expectedArtist], albumTitle: expectedAlbumTitle},
            {artists: [expectedArtist], albumTitle: expectedAlbumTitle},
          ],
        };

        const wrapper = render(
            <ProductGroupEditor productGroup={productGroup}/>,
        );

        const productGroupElement = wrapper.getByTestId('product-group');
        const albumElements = within(productGroupElement)
            .queryAllByTestId('album');
        expect(albumElements).toHaveLength(3);
      });
    });
  });

  describe('adding albums', () => {
    describe('when there are no products', () => {
      it('can add products', async () => {
        const queryText = 'Bobby';
        const noProducts = {
          products: [],
        };

        const results = [
          {
            artists: [{name: 'Bobby Joel'}],
          },
        ];

        when(searchService).calledWith(queryText)
            .mockReturnValue(Promise.resolve(results));

        const wrapper = render(
            <ProductGroupEditor productGroup={noProducts} />,
        );

        const searchInput = wrapper.getByTestId('search-input');

        userEvent.type(searchInput, queryText);

        userEvent.click(wrapper.getByTestId('search-button'));

        // wait for re-render of react components to occur
        await waitFor(async () => {
          userEvent.click(wrapper.getByText('Add'));
        });

        await waitFor(async () => {
          const productGroupElement = wrapper.getByTestId('product-group');

          const albumElements = within(productGroupElement)
              .getAllByTestId('album');
          expect(albumElements).toHaveLength(1);
        });
      });

      it('displays search results with album details', async () => {
        const queryText = 'Bobby';
        const noProducts = {
          products: [],
        };

        const expectedArtist = {name: 'Bobby Joel'};
        const expectedAlbum = {
          artistName: {name: 'Bobby Joel'}.name,
          albumTitle: 'Subway entrance thing',
          albumAltText: 'Bobby Joel - Subway entrance thing',
          albumImage: 'http://example.com/image.png',
        };

        const results = [{
          artists: [expectedArtist],
          imageUrl: expectedAlbum.albumImage,
          albumTitle: expectedAlbum.albumTitle,
        }];

        when(searchService).calledWith(queryText)
            .mockReturnValue(Promise.resolve(results));

        const wrapper = render(
            <ProductGroupEditor productGroup={noProducts} />,
        );

        const searchInput = wrapper.getByTestId('search-input');

        userEvent.type(searchInput, queryText);

        userEvent.click(wrapper.getByTestId('search-button'));

        await waitFor(async () => {
          const searchResults = wrapper.getByTestId('search-results');
          expectAlbum(searchResults, expectedAlbum);
        });
      });
    });
  });

  function expectAlbum(container, expectedAlbum) {
    expect(getByText(container, expectedAlbum.artistName)).toBeInTheDocument();
    expect(getByText(container, expectedAlbum.albumTitle)).toBeInTheDocument();
    const albumArt = getByAltText(container, expectedAlbum.albumAltText);

    expect(albumArt).toBeInTheDocument();
    expect(albumArt.getAttribute('src')).toEqual(expectedAlbum.albumImage);
  }
});
